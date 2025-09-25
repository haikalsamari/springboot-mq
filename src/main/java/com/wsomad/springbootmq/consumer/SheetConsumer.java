package com.wsomad.springbootmq.consumer;

import com.wsomad.springbootmq.config.RabbitMQConfig;
import com.wsomad.springbootmq.service.GoogleSheetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

@Service
public class SheetConsumer {
    @Value("${spreadsheet-id}")
    private String spreadsheetId;
    private final GoogleSheetService googleSheetService;
    private static final Logger log = LoggerFactory.getLogger(SheetConsumer.class);

    public SheetConsumer(GoogleSheetService googleSheetService) {
        this.googleSheetService = googleSheetService;
    }

    @RabbitListener(queues = RabbitMQConfig.SHEET_QUEUE)
    public void receive(String text) {
        System.out.println("Writing to Google Sheets: " + text);
        try {
            String sheetName = "Sheet1";
            googleSheetService.appendRow(spreadsheetId, sheetName, Arrays.asList(text, LocalDateTime.now().toString()));
        } catch (IOException e) {
            log.error("e: ", e);
        }
    }
}
