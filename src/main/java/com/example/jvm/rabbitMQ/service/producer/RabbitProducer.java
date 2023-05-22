package com.example.jvm.rabbitMQ.service.producer;

import com.rabbitmq.client.MessageProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class RabbitProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitProducer.class);
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Value("${rabbitmq.topic.exchange.name}")
    private String topicExchange;
    @Value("${rabbitmq.fanout.exchange.name}")
    private String fanoutExchange;
    @Value("${rabbitmq.header.exchange.name}")
    private String headerExchange;
    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    public void sendMessage(String message) {
        LOGGER.info(String.format("Sending message: -> %s", message));
        rabbitTemplate.convertAndSend(topicExchange, routingKey, message);
    }

    public void sendFanoutMessage(String message) {
        LOGGER.info(String.format("Sending message: -> %s", message));
        rabbitTemplate.convertAndSend(fanoutExchange, "", message);
    }
//    public void sendHeaderMessage(String department,String message) {
//        MessageProperties properties = new MessageProperties();
//        properties.setHeader("students", "John, Jane, James");
//        rabbitTemplate.convertAndSend(headerExchange,"", message);
//    }
}
