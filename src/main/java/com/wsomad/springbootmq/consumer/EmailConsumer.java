package com.wsomad.springbootmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class EmailConsumer {
    @RabbitListener(queues = "email-queue")
    public void receive(String message) {
        System.out.println("📧 Email Service got: " + message);
    }
}
