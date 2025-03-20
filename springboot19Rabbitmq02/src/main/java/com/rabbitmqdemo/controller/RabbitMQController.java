package com.rabbitmqdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rabbitmqdemo.model.Message;
import com.rabbitmqdemo.producer.MessageProducer;

@RestController
@RequestMapping("/api/rabbitmq")
public class RabbitMQController {

    private final MessageProducer messageProducer;

    @Autowired
    public RabbitMQController(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody Message message) {
        messageProducer.sendMessage(message);
        return ResponseEntity.ok("Message sent to RabbitMQ");
    }

    @PostMapping("/send-from-file")
    public ResponseEntity<String> sendFromFile(@RequestParam String filePath) {
        messageProducer.sendMessagesFromFile(filePath);
        return ResponseEntity.ok("Messages from file sent to RabbitMQ");
    }
}