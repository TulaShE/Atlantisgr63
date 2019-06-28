/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr63.atlantis.message;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Envelope;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author dev
 */
@Singleton
@Startup
public class MessageReceiver {

    private final static String QUEUE_SEND = "metrics_to_net";
    private final static String QUEUE_RECEIVE = "ask_to_jee";
    
    @PostConstruct
    void init() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("admin");
        factory.setPassword("atlantisjee");
        factory.setVirtualHost("/");
        factory.setHost("192.168.1.73");
        factory.setPort(5672);//amqp://admin:atlantisjee@localhost:5672/
        Connection connection = null;
        try {
            connection = factory.newConnection();
        } catch (IOException | TimeoutException ex) {
            Logger.getLogger(MessageReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Channel channel = null;
        try {
            channel = connection.createChannel();
        } catch (IOException ex) {
            Logger.getLogger(MessageReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            channel.queueDeclare(QUEUE_RECEIVE, false, false, false, null);
        } catch (IOException ex) {
            Logger.getLogger(MessageReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(" [*] Waiting for messages [*]");
        
        Consumer consumer = new DefaultConsumer(channel){
            
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties pr, byte[] body) throws IOException{
                String message = new String(body, "UTF-8");
                
                System.out.println(" [x] Received : " + message);
                
                if("AllMetrics".equals(message)){
                    send();
                }
            }
        };
        
        try {
            channel.basicConsume(QUEUE_RECEIVE, true, consumer);
        } catch (IOException ex) {
            Logger.getLogger(MessageReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void send(){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("admin");
        factory.setPassword("atlantisjee");
        factory.setVirtualHost("/");
        factory.setHost("192.168.1.73");
        factory.setPort(5672);//amqp://admin:atlantisjee@localhost:5672/
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            
            channel.queueDeclare(QUEUE_SEND, false, false, false, null);
            
            String message ="You did it";
            
            channel.basicPublish("", QUEUE_SEND, null, message.getBytes());
            
            System.out.println(" [x] Sent : " + message);
            
            channel.close();
            connection.close();

        } catch (IOException | TimeoutException ex) {
            Logger.getLogger(MessageReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
