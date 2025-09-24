package com.wsomad.springbootmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class AppConsumer {
    @RabbitListener(queues = "app-queue")
    public void receive(String message) {
        System.out.println("📱 App Service got: " + message);
    }
}
