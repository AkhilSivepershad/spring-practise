package com.example.springpractise.service;

import com.example.springpractise.model.SensitiveWord;
import com.example.springpractise.repository.SensitiveWordRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SensitiveWordService {
    private final SensitiveWordRepository sensitiveWordRepository;
    public SensitiveWordService(SensitiveWordRepository sensitiveWordRepository){
        this.sensitiveWordRepository=sensitiveWordRepository;
    }

    public SensitiveWord createSensitiveWord(SensitiveWord newSensitiveWord){
        return sensitiveWordRepository.save(newSensitiveWord);
    }

    public List<SensitiveWord> getSensitiveWords() {
        Iterable<SensitiveWord> sensitiveWordIterable = sensitiveWordRepository.findAll();
        List<SensitiveWord> result = new ArrayList<>();
        sensitiveWordIterable.forEach(result::add);

        return result;
    }
    public List<SensitiveWord> getByWord(String word) {
        Iterable<SensitiveWord> sensitiveWordIterable = sensitiveWordRepository.findByWord(word);
        List<SensitiveWord> result = new ArrayList<>();
        sensitiveWordIterable.forEach(result::add);

        return result;
    }

    public SensitiveWord updateSensitiveWord(SensitiveWord newSensitiveWord){
        return sensitiveWordRepository.save(newSensitiveWord);
    }

    public void deleteSensitiveWord(SensitiveWord sensitiveWordToDelete){
        sensitiveWordRepository.delete(sensitiveWordToDelete);
    }
}
