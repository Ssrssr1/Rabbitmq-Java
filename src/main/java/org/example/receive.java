package org.example;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class receive {

    public static void main(String[] argv) throws Exception {
        Properties consumerProps = new Properties();
        FileInputStream fis = new FileInputStream("/home/ljh/workspace/github/Rabbitmq-Java/src/main/java/rabbitmq.properties");
        consumerProps.load(fis);
        String host = consumerProps.getProperty("spring.rabbitmq.host");
        String user = consumerProps.getProperty("spring.rabbitmq.username");
        String pass = consumerProps.getProperty("spring.rabbitmq.password");
        int port = Integer.parseInt(consumerProps.getProperty("spring.rabbitmq.port"));
        String quene = consumerProps.getProperty("spring.rabbitmq.quene");
        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost(host);
        factory.setUsername(user);
        factory.setPassword(pass);
        factory.setPort(port);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(quene, false, false, false, null);
        System.out.println("Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println("Received '" + message + "'");
        };
        channel.basicConsume(quene, true, deliverCallback, consumerTag -> { });
    }
}