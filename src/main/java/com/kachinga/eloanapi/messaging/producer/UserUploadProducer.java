package com.kachinga.eloanapi.messaging.producer;

import com.kachinga.eloanapi.domain.payload.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUploadProducer {
    @Autowired
    private RabbitTemplate amqpTemplate;

    @Value("${RABBIT_USER_UPLOAD_EXCHANGE}")
    private String exchange;

    @Value("${RABBIT_USER_UPLOAD_ROUTING_KEY}")
    private String routingKey;

    public void produce(RegisterRequest item) {
        amqpTemplate.convertAndSend(exchange, routingKey, item);
    }
}