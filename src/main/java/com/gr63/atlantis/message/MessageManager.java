/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr63.atlantis.message;

import com.gr63.atlantis.business.logic.MetricServiceLocal;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author dev
 */
@Singleton
@Startup
public class MessageManager {

    private final static String QUEUE_NAME = "metrics";
    
    @Inject
    private MetricServiceLocal metricService;
    
    public void receive(){
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setUsername("admin");
            factory.setPassword("atlantisjee");
            factory.setVirtualHost("/");
            factory.setHost("192.168.1.73");
            factory.setPort(5672);
            
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println("[*] Waiting message [*]");
            
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [*] Message received " + message);
                
                metricService.addMetric(message);
            };
            
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
        } catch (IOException | TimeoutException ex) {
            Logger.getLogger(MessageManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
