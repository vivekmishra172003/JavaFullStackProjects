package com.rabbitmqdemo.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Exchange name from application.properties
    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;
    
    // Queue names from application.properties
    @Value("${rabbitmq.queue.inq}")
    private String inqQueueName;
    
    @Value("${rabbitmq.queue.outq}")
    private String outqQueueName;
    
    // Routing keys from application.properties
    @Value("${rabbitmq.routing.key.inq}")
    private String inqRoutingKey;
    
    @Value("${rabbitmq.routing.key.outq}")
    private String outqRoutingKey;

    // Create Direct Exchange
    @Bean
    public DirectExchange exchange() {
        // Creates a durable exchange (survives broker restart)
        return new DirectExchange(exchangeName);
    }

    // Create APPINQ Queue
    @Bean
    public Queue inqQueue() {
        // Creates a durable queue (survives broker restart)
        return new Queue(inqQueueName);
    }

    // Create APPOUTQ Queue
    @Bean
    public Queue outqQueue() {
        return new Queue(outqQueueName);
    }

    // Bind APPINQ Queue to Exchange with routing key
    @Bean
    public Binding inqBinding() {
        // Connect the queue to the exchange with routing key
        return BindingBuilder
                .bind(inqQueue())
                .to(exchange())
                .with(inqRoutingKey);
    }

    // Bind APPOUTQ Queue to Exchange with routing key
    @Bean
    public Binding outqBinding() {
        return BindingBuilder
                .bind(outqQueue())
                .to(exchange())
                .with(outqRoutingKey);
    }

    // Configure how messages are converted (JSON in this case)
    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    // Add this new bean definition for RabbitAdmin
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    // Configure RabbitTemplate with our converter
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}