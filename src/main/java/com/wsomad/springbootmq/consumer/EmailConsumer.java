package com.wsomad.springbootmq.consumer;

import com.wsomad.springbootmq.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailConsumer {
    @Value("${recipient-email}")
    private String recipientEmail;
    private final JavaMailSender javaMailSender;

    public EmailConsumer(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @RabbitListener(queues = RabbitMQConfig.EMAIL_QUEUE)
    public void receive(String text) {
        System.out.println("Sending email: " + text);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(recipientEmail);
        simpleMailMessage.setSubject("Test Transaction Notification");
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);
    }
}
