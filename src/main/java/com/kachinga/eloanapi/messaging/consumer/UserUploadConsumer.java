package com.kachinga.eloanapi.messaging.consumer;

import com.kachinga.eloanapi.domain.User;
import com.kachinga.eloanapi.domain.payload.RegisterRequest;
import com.kachinga.eloanapi.repository.UserRepository;
import com.kachinga.eloanapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserUploadConsumer {
    private final UserRepository userRepository;
    private final UserService userService;

    @RabbitListener(queues = "${RABBIT_USER_UPLOAD_QUEUE}", containerFactory = "jsaFactory")
    public void receivedMessage(RegisterRequest item) throws ParseException {
        Optional<User> checkNumber = userRepository.findFirstByNumberAndCompanyId(item.getNumber(), item.getCompany().getId());

        if (!checkNumber.isPresent()) {
            Optional<User> checkMobile = userRepository.findByMobile(item.getMobile());
            Optional<User> checkUsername = userRepository.findByUsername(item.getUsername());
            Optional<User> checkEmail = userRepository.findByEmail(item.getEmail());
            if (checkMobile.isPresent()) {
                User user = userService.convertToEntity(item);
                user.setId(item.getId());
                userRepository.save(user);
                log.warn(LocalDateTime.now() + " - " + item.getCompany().getName() + " - USER_UPLOAD - " + item.getName() + " already uploaded");
            } else if (checkUsername.isPresent()) {
                User user = userService.convertToEntity(item);
                user.setId(item.getId());
                userRepository.save(user);
                log.warn(LocalDateTime.now() + " - " + item.getCompany().getName() + " - USER_UPLOAD - " + item.getName() + " already uploaded");
            } else if (checkEmail.isPresent()) {
                User user = userService.convertToEntity(item);
                user.setId(item.getId());
                userRepository.save(user);
                log.warn(LocalDateTime.now() + " - " + item.getCompany().getName() + " - USER_UPLOAD - " + item.getName() + " already uploaded");
            } else {
                User user = userService.convertToEntity(item);
                user.setId(null);
                userRepository.save(user);
                log.info(LocalDateTime.now() + " - " + item.getCompany().getName() + " - USER_UPLOAD - " + item.getName() + " uploaded successfully!");
            }
        } else {
            User user = checkNumber.get();
            userRepository.save(user);
            log.warn(LocalDateTime.now() + " - " + item.getCompany().getName() + " - USER_UPLOAD - " + item.getName() + " already uploaded");
        }
    }
}