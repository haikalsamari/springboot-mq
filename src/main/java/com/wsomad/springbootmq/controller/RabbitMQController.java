package com.wsomad.springbootmq.controller;

import com.wsomad.springbootmq.producer.TransactionProducer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mq")
public class RabbitMQController {
    private final TransactionProducer transactionProducer;

    public RabbitMQController(TransactionProducer transactionProducer) {
        this.transactionProducer = transactionProducer;
    }

    @PostMapping
    public String send(@RequestParam String message) {
        transactionProducer.sendTransactionEvent(message);
        return "Message sent: " + message;
    }
}
