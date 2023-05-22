package com.example.jvm.rabbitMQ.service.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consume(String message) {
        LOGGER.info(String.format("Received message: -> %s", message));
    }


    @RabbitListener(queues = {"${rabbitmq.queue.a}"
            , "${rabbitmq.queue.b}"
            , "${rabbitmq.queue.c}"
            , "${rabbitmq.queue.d}"})
    public void receiveFromAppleQueue(String message) {
        LOGGER.info(String.format("Received message from fan-out exchange : -> %s", message));
    }


}
