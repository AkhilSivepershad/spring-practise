package com.example.springpractise.controller;

import com.example.springpractise.model.SensitiveWord;
import com.example.springpractise.repository.SensitiveWordRepository;
import com.example.springpractise.service.SensitiveWordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("sensitiveWords")
public class SensitiveWordController {
    private final SensitiveWordService sensitiveWordService;
    public SensitiveWordController(SensitiveWordService sensitiveWordService){
        this.sensitiveWordService=sensitiveWordService;
    }

    @GetMapping()
    public ResponseEntity<?> getSensitiveWords() {
        List<SensitiveWord> sensitiveWords = this.sensitiveWordService.getSensitiveWords();
        if (!sensitiveWords.isEmpty()) {
            return new ResponseEntity<>(sensitiveWords, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("{word}")
    public ResponseEntity<?> getSensitiveWord(@PathVariable String word) {
        return this.sensitiveWordService.getByWord(word)
                .map(sensitiveWord -> new ResponseEntity<>(sensitiveWord, HttpStatus.FOUND))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("create")
    public ResponseEntity<?> createSensitiveWord(@RequestBody String word) {
        final SensitiveWord savedSensitiveWord = sensitiveWordService.createSensitiveWord(word);
        return new ResponseEntity<>(savedSensitiveWord, HttpStatus.CREATED);
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> deleteSensitiveWord(@RequestBody SensitiveWord sensitiveWord) {
        sensitiveWordService.deleteSensitiveWord(sensitiveWord);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
