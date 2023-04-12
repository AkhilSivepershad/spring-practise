package com.example.springpractise.service;

import com.example.springpractise.model.SensitiveWord;
import com.example.springpractise.repository.SensitiveWordRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SensitiveWordService {
    private final SensitiveWordRepository sensitiveWordRepository;
    public SensitiveWordService(SensitiveWordRepository sensitiveWordRepository){
        this.sensitiveWordRepository=sensitiveWordRepository;
    }

    public SensitiveWord  createSensitiveWord(String newWord){
        return getByWord(newWord).orElseGet(() -> sensitiveWordRepository.save(new SensitiveWord(newWord)));
    }

    public List<SensitiveWord> getSensitiveWords() {
        Iterable<SensitiveWord> sensitiveWordIterable = sensitiveWordRepository.findAll();
        List<SensitiveWord> result = new ArrayList<>();
        sensitiveWordIterable.forEach(result::add);

        return result;
    }
    public Optional<SensitiveWord> getByWord(String word) {
        return sensitiveWordRepository.findByWord(word);
    }
    public Optional<Set<SensitiveWord>> getByListOfWords(Set<String> words) {
        return sensitiveWordRepository.findAllByWordIn(words);
    }
    public void deleteSensitiveWord(SensitiveWord sensitiveWordToDelete){
        sensitiveWordRepository.delete(sensitiveWordToDelete);
    }
}
