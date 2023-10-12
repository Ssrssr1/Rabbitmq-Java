package org.example;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class send {
    public static void main(String[] argv) throws Exception {
        Properties producerProps = new Properties();
        FileInputStream fis = new FileInputStream("/home/ljh/workspace/github/Rabbitmq-Java/src/main/java/rabbitmq.properties");
        producerProps.load(fis);
        String host = producerProps.getProperty("spring.rabbitmq.host");
        String user = producerProps.getProperty("spring.rabbitmq.username");
        String pass = producerProps.getProperty("spring.rabbitmq.password");
        int port = Integer.parseInt(producerProps.getProperty("spring.rabbitmq.port"));
        String quene = producerProps.getProperty("spring.rabbitmq.quene");
        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost(host);
        factory.setUsername(user);
        factory.setPassword(pass);
        factory.setPort(port);

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(quene, false, false, false, null);
            String message = "Hello World!";
            channel.basicPublish("", quene, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println("Sent '" + message + "'");
        }
    }
}