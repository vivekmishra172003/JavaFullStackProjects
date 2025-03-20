package com.rabbitmqdemo.producer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rabbitmqdemo.model.Message;

@Service
public class MessageProducer {

    private final RabbitTemplate rabbitTemplate;
    
    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;
    
    @Value("${rabbitmq.routing.key.inq}")
    private String routingKeyInq;

    public MessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    // Send a single message
    public void sendMessage(Message message) {
        rabbitTemplate.convertAndSend(exchangeName, routingKeyInq, message);
        System.out.println("Message sent: " + message);
    }
    
    // Read messages from file and send them
    public void sendMessagesFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Message message = new Message(UUID.randomUUID().toString(), line);
                sendMessage(message);
            }
            System.out.println("All messages from file sent successfully!");
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}