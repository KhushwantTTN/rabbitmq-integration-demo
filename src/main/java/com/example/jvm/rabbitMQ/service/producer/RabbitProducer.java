package com.example.jvm.rabbitMQ.service.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitProducer {

    @Value("${rabbitmq.topic.exchange.name}")
    private String topicExchange;

    @Value("${rabbitmq.fanout.exchange.name}")
    private String fanoutExchange;


    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitProducer.class);

    @Autowired
    RabbitTemplate rabbitTemplate;


    public void sendMessage(String message) {
        LOGGER.info(String.format("Sending message: -> %s", message));
        rabbitTemplate.convertAndSend(topicExchange, routingKey, message);
    }

    public void sendFanoutMessage(String message) {
        LOGGER.info(String.format("Sending message: -> %s", message));
        rabbitTemplate.convertAndSend(fanoutExchange, "", message);
    }
}
