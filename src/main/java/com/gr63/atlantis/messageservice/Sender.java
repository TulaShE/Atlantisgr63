package com.gr63.atlantis.messageservice;


import com.gr63.atlantis.integration.MetricDAO;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dev
 */
@Singleton
@Startup
public class Sender {
    private final static String QUEUE_NAME = "hello";
    
    private MetricDAO metricDAO;
    
    public void send() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("admin");
        factory.setPassword("atlantisjee");
        factory.setVirtualHost("/");
        factory.setHost("192.168.1.73");
        factory.setPort(5672);//amqp://admin:atlantisjee@localhost:5672/
        try (Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()){
                channel.queueDeclare(QUEUE_NAME, false, false, false, null);
                String message = "Hello World !";
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                System.out.println(message);
                
            }
    }
    
    
    public void receive() throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("admin");
        factory.setPassword("atlantisjee");
        factory.setVirtualHost("/");
        factory.setHost("192.168.1.73");
        factory.setPort(5672);//amqp://admin:atlantisjee@localhost:5672/
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println("Waiting message");
        
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(),"UTF-8");
            
            System.out.println("Received : " + message);
        };
        
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}
