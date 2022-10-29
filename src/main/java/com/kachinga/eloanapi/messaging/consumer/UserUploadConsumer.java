package com.kachinga.eloanapi.messaging.consumer;

import com.kachinga.eloanapi.domain.User;
import com.kachinga.eloanapi.domain.payload.RegisterRequest;
import com.kachinga.eloanapi.repository.UserRepository;
import com.kachinga.eloanapi.service.UserService;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserUploadConsumer implements ChannelAwareMessageListener {
    private final UserRepository userRepository;
    private final UserService userService;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        System.out.println("Received <" + message + ">");
        byte[] byteArray = message.getBody();
        RegisterRequest item = (RegisterRequest) getObject(byteArray);
        processData(item, message, channel);
    }

    public void processData(RegisterRequest item, Message message, Channel channel) throws IOException {
        Optional<User> checkNumber = userRepository.findFirstByNumberAndCompanyId(item.getNumber(), item.getCompany().getId());

        try {
            if (checkNumber.isPresent()) {
                User user = checkNumber.get();
                userRepository.save(user);
                log.warn(LocalDateTime.now() + " - " + item.getCompany().getName() + " - USER_UPLOAD - " + item.getName() + " already uploaded");
            } else {
                Optional<User> checkMobile = userRepository.findByCompanyIdAndMobile(item.getCompany().getId(), item.getMobile());
                Optional<User> checkUsername = userRepository.findByCompanyIdAndUsername(item.getCompany().getId(), item.getUsername());
                Optional<User> checkEmail = userRepository.findByCompanyIdAndEmail(item.getCompany().getId(), item.getEmail());
                if (checkMobile.isPresent()) {
                    User user = checkMobile.get();
                    userRepository.save(user);
                    log.warn(LocalDateTime.now() + " - " + item.getCompany().getName() + " - USER_UPLOAD - " + item.getName() + " already uploaded");
                } else if (checkUsername.isPresent()) {
                    User user = checkUsername.get();
                    userRepository.save(user);
                    log.warn(LocalDateTime.now() + " - " + item.getCompany().getName() + " - USER_UPLOAD - " + item.getName() + " already uploaded");
                } else if (checkEmail.isPresent()) {
                    User user = checkEmail.get();
                    userRepository.save(user);
                    log.warn(LocalDateTime.now() + " - " + item.getCompany().getName() + " - USER_UPLOAD - " + item.getName() + " already uploaded");
                } else {
                    User user = userService.convertToEntity(item);
                    user.setId(null);
                    userRepository.save(user);
                    log.info(LocalDateTime.now() + " - " + item.getCompany().getName() + " - USER_UPLOAD - " + item.getName() + " uploaded successfully!");
                }
            }
            assert channel != null;
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            e.printStackTrace();
            assert channel != null;
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }
    }

    private static Object getObject(byte[] byteArray) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(byteArray);
        ObjectInput in = new ObjectInputStream(bis);
        return in.readObject();
    }
}