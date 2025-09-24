package com.wsomad.springbootmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class CalendarConsumer {
    @RabbitListener(queues = "calendar-queue")
    public void receive(String message) {
        System.out.println("📅 Calendar Service got: " + message);
    }
}
