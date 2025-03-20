package com.rabbitmqdemo.util;

import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueueUtility {

    private final RabbitAdmin rabbitAdmin;

    @Autowired
    public QueueUtility(RabbitAdmin rabbitAdmin) {
        this.rabbitAdmin = rabbitAdmin;
    }

    public void deleteQueue(String queueName) {
        rabbitAdmin.deleteQueue(queueName);
        System.out.println("Queue deleted: " + queueName);
    }
}