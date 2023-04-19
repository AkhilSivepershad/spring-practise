package com.example.springpractise.controller;

import com.example.springpractise.service.SensitiveWordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.function.Supplier;

@RestController
@RequestMapping("sensitiveWords")
public class SensitiveWordController {
    private final SensitiveWordService sensitiveWordService;
    public SensitiveWordController(SensitiveWordService sensitiveWordService) {
        this.sensitiveWordService = sensitiveWordService;
    }
    private ResponseEntity<?> handleResponse(Supplier<Object> serviceCall) {
        try {
            Object result = serviceCall.get();
            return new ResponseEntity<>(result, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Word does not exist",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping()
    public ResponseEntity<?> getSensitiveWords() {
        return handleResponse(() -> sensitiveWordService.getSensitiveWords());
    }

    @GetMapping("{word}")
    public ResponseEntity<?> getSensitiveWord(@PathVariable String word) {
        return handleResponse(() -> sensitiveWordService.getByWord(word));
    }

    @PostMapping("create")
    public ResponseEntity<?> createSensitiveWord(@RequestBody String word) {
        try{
            return new ResponseEntity<>(sensitiveWordService.createSensitiveWord(word), HttpStatus.CREATED);
        }
        catch (IllegalArgumentException E){
            return new ResponseEntity<>("Word already exists",HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> deleteSensitiveWord(@RequestBody String word) {
        try{
            sensitiveWordService.deleteSensitiveWord(word);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (EntityNotFoundException E){
            return new ResponseEntity<>("Word does not exist",HttpStatus.NOT_FOUND);
        }
//        return handleResponse(() -> sensitiveWordService.deleteSensitiveWord(word));
    }
}
