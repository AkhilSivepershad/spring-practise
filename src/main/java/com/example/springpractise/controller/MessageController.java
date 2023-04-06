package com.example.springpractise.controller;

import com.example.springpractise.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("message")
public class MessageController {
    private final MessageService messageService;
    public MessageController(MessageService messageService){
        this.messageService =messageService;
    }
    @PostMapping()
    public ResponseEntity<?> processMessage(@RequestBody String message) {
        return new ResponseEntity<>(messageService.processMessage(message), HttpStatus.OK);
    }
}
