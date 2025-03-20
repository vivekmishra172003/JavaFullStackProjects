package com.rabbitmqdemo.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.rabbitmqdemo.model.Message;

@Service
public class MessageConsumer {

    // This method is called whenever a message arrives in the queue
    @RabbitListener(queues = "${rabbitmq.queue.inq}")
    public void consumeMessage(Message message) {
        System.out.println("Received message: " + message);
    }
}