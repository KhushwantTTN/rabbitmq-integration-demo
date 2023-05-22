package com.example.jvm.rabbitMQ.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RabbitConfig {

    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Value("${rabbitmq.topic.exchange.name}")
    private String topicExchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;


    @Bean
    public Queue queue() {
        return new Queue(queueName);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(topicExchange);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(topicExchange()).with(routingKey);
    }

    @Value("${rabbitmq.fanout.exchange.name}")
    private String fanoutExchange;
    @Value("${rabbitmq.queue.a}")
    private String appleQueue;
    @Value("${rabbitmq.queue.b}")
    private String basketQueue;

    @Value("${rabbitmq.queue.c}")
    private String catQueue;
    @Value("${rabbitmq.queue.d}")
    private String dogQueue;

    @Bean
    public Queue appleQueue() {
        return new Queue(appleQueue);
    }

    @Bean
    public Queue basketQueue() {
        return new Queue(basketQueue);
    }

    @Bean
    public Queue catQueue() {
        return new Queue(catQueue);
    }

    @Bean
    public Queue dogQueue() {
        return new Queue(dogQueue);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(fanoutExchange);
    }

    @Bean
    public List<Binding> bindings() {
        List<Binding> bindings = new ArrayList<>();

        bindings.add(BindingBuilder.bind(appleQueue()).to(fanoutExchange()));
        bindings.add(BindingBuilder.bind(basketQueue()).to(fanoutExchange()));
        bindings.add(BindingBuilder.bind(catQueue()).to(fanoutExchange()));
        bindings.add(BindingBuilder.bind(dogQueue()).to(fanoutExchange()));

        return bindings;
    }

    @Value("${rabbitmq.header.exchange.name}")
    private String headerExchange;


    @Bean
    public HeadersExchange headerExchange() {
        return new HeadersExchange(headerExchange);
    }
    @Bean
    public List<Binding> headerBindings() {
        List<Binding> bindings = new ArrayList<>();

        bindings.add(BindingBuilder.bind(appleQueue()).to(headerExchange()).where("queue").matches("apple"));
        bindings.add(BindingBuilder.bind(basketQueue()).to(headerExchange()).where("queue").matches("basket"));
        bindings.add(BindingBuilder.bind(catQueue()).to(headerExchange()).where("queue").matches("cat"));
        bindings.add(BindingBuilder.bind(dogQueue()).to(headerExchange()).where("queue").matches("dog"));

        return bindings;
    }

}

