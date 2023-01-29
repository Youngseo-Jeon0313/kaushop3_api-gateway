package com.example.kaushoporder.service;

import com.example.kaushoporder.model.StockMessageDto;
import com.example.kaushoporder.model.TransactionPhase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class SendMessageService {
    private static final String QUEUE_NAME = "200212206-stock-queue";
    private final QueueMessagingTemplate messagingTemplate;
    private final ObjectMapper mapper;

    public void send(StockMessageDto payload) {
        try {
            Message msg = MessageBuilder.withPayload(mapper.writeValueAsString(payload))
                    .setHeader("tid", UUID.randomUUID())
                    .setHeader("phase", TransactionPhase.TRY.getType())
                    .build();

            messagingTemplate.send(QUEUE_NAME, msg);
            log.info("Message sent - productId : {}, stockChanged : {}", payload.getProductId(), payload.getStockChanged());
        } catch(JsonProcessingException e) {
            log.error("Serializing payload failed.");
        }
    }
}
