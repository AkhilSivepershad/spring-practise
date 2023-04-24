package com.example.springpractise.messageQueueProcess;

import com.example.springpractise.model.Message;
import com.example.springpractise.service.MessageService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageQueueProcessor {

    private final MessageService messageService;
    private final JmsTemplate jmsTemplate;

    public MessageQueueProcessor(MessageService messageService, JmsTemplate jmsTemplate) {
        this.messageService = messageService;
        this.jmsTemplate = jmsTemplate;
    }

    @JmsListener(destination = "input-queue")
    public void messageListener(String systemMessage) {
        System.out.println(systemMessage.toString());
        String processedMessage = messageService.processMessage(systemMessage);
        //messagePublisher(processedMessage,systemMessage.getId());
        messagePublisher(processedMessage);
    }

    //public void messagePublisher(String processedMessage,String id) {
    public void messagePublisher(String processedMessage) {
        System.out.println(processedMessage);
//        Message newMessage=new Message();
//        newMessage.setMessage(processedMessage);
//        newMessage.setId(id);
        jmsTemplate.convertAndSend("output-queue", processedMessage);
    }
}