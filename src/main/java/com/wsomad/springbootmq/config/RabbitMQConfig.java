package com.wsomad.springbootmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitMQConfig {
    public static final String FANOUT_EXCHANGE = "transaction-exchange";
    public static final String EMAIL_QUEUE = "email-queue";
    public static final String SHEET_QUEUE = "sheet-queue";

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    public Queue emailQueue() {
        return new Queue(EMAIL_QUEUE, false);
    }

    @Bean
    public static Queue sheetQueue() {
        return new Queue(SHEET_QUEUE, false);
    }

    @Bean
    public Queue appQueue() {
        return new Queue("app-queue", false);
    }

    @Bean
    public Queue calendarQueue() {
        return new Queue("calendar-queue", false);
    }

    @Bean
    public Binding bindingEmail(FanoutExchange fanoutExchange, Queue emailQueue) {
        return BindingBuilder.bind(emailQueue).to(fanoutExchange);
    }

    @Bean
    public Binding bindingApp(FanoutExchange fanoutExchange, Queue appQueue) {
        return BindingBuilder.bind(appQueue).to(fanoutExchange);
    }

    @Bean
    public Binding bindingCalendar(FanoutExchange fanoutExchange, Queue calendarQueue) {
        return BindingBuilder.bind(calendarQueue).to(fanoutExchange);
    }

    @Bean
    public Binding sheetBinding(Queue sheetQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(sheetQueue).to(fanoutExchange);
    }
}
