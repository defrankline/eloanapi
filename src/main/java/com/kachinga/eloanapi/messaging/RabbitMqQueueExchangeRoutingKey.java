package com.kachinga.eloanapi.messaging;

import com.kachinga.eloanapi.messaging.consumer.UserUploadConsumer;
import com.kachinga.eloanapi.messaging.producer.UserUploadProducer;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
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

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setQueueNames(queueNameUserUpload);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(UserUploadConsumer userUploadConsumer){
        return new MessageListenerAdapter(userUploadConsumer,"receiveMessage");
    }
}
