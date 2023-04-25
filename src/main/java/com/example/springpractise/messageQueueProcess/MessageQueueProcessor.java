package com.example.springpractise.messageQueueProcess;

import com.example.springpractise.model.MessageModel;
import com.example.springpractise.service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Component
public class MessageQueueProcessor {

    private final MessageService messageService;
    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

    public MessageQueueProcessor(MessageService messageService, JmsTemplate jmsTemplate) {
        this.messageService = messageService;
        this.jmsTemplate = jmsTemplate;
        objectMapper = new ObjectMapper();
    }

    @JmsListener(destination = "input-queue")
    public void messageListener(Message systemMessage) {
        try {
            String messageText = ((TextMessage) systemMessage).getText();
            MessageModel messageModel = objectMapper.readValue(messageText, MessageModel.class);
            messageModel.setMessage(messageService.processMessage(messageModel.getMessage()));
            messagePublisher(messageModel);

        } catch (JsonProcessingException | JMSException e) {
            throw new RuntimeException(e);
        }
    }

    public void messagePublisher(MessageModel processedMessageModel) throws JsonProcessingException {
        System.out.println(processedMessageModel);
        jmsTemplate.convertAndSend("output-queue", objectMapper.writeValueAsString(processedMessageModel));
    }

}