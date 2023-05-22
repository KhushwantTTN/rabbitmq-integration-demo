package com.example.jvm.rabbitMQ.controller;

import com.example.jvm.rabbitMQ.service.producer.RabbitProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer")
public class RabbitController {


    @Autowired
    RabbitProducer rabbitProducer;

    @GetMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestParam String message){
        rabbitProducer.sendMessage(message);
        return ResponseEntity.ok("Message sent successfully");
    }

    @GetMapping("/send/fanout")
    public ResponseEntity<String> sendFanoutMessage(@RequestParam String message){
        rabbitProducer.sendFanoutMessage(message);
        return ResponseEntity.ok("Message sent successfully");
    }

    @GetMapping("/hello")
    private ResponseEntity<String> stringResponseEntity(){
        return ResponseEntity.ok("hello");
    }
}
