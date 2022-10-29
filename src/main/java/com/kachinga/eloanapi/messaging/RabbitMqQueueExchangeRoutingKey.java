package com.kachinga.eloanapi.messaging;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqQueueExchangeRoutingKey {
    @Value("${RABBIT_USER_UPLOAD_QUEUE}")
    String queueNameUserUpload;

    @Value("${RABBIT_USER_UPLOAD_EXCHANGE}")
    String exchangeUserUpload;

    @Value("${RABBIT_USER_UPLOAD_ROUTING_KEY}")
    private String routingKeyUserUpload;
    @Bean
    Queue queueUserUpload() {
        return new Queue(queueNameUserUpload, false);
    }

    @Bean
    DirectExchange exchangeUserUpload() {
        return new DirectExchange(exchangeUserUpload);
    }

    @Bean
    Binding bindingUserUpload(Queue queueUserUpload, DirectExchange exchangeUserUpload) {
        return BindingBuilder.bind(queueUserUpload).to(exchangeUserUpload).with(routingKeyUserUpload);
    }
}
