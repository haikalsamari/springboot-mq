package com.wsomad.springbootmq.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransactionProducer {
    private final AmqpTemplate amqpTemplate;

    public TransactionProducer(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendTransactionEvent(String message) {
        amqpTemplate.convertAndSend("transaction-exchange", "", message);
        System.out.println("Message sent: " + message);
    }
}
