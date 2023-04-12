package com.example.springpractise.service;

import com.example.springpractise.model.SensitiveWord;
import com.example.springpractise.repository.SensitiveWordRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
        try {
           return sensitiveWordRepository.save(new SensitiveWord(newWord));
        }catch (Exception E){
            throw new IllegalArgumentException();
        }
    }

    public List<SensitiveWord> getSensitiveWords() {
        Iterable<SensitiveWord> sensitiveWordIterable = sensitiveWordRepository.findAll();
        List<SensitiveWord> result = new ArrayList<>();
        sensitiveWordIterable.forEach(result::add);

        if(result.size()>0){
            return result;
        }
        else{
            throw new EntityNotFoundException();
        }
    }
    public SensitiveWord getByWord(String word) {
        return sensitiveWordRepository.findByWord(word).orElseThrow(EntityNotFoundException::new);
    }
    public Optional<Set<SensitiveWord>> getByListOfWords(Set<String> words) {
        return sensitiveWordRepository.findAllByWordIn(words);
    }
    public void deleteSensitiveWord(SensitiveWord sensitiveWordToDelete){
        sensitiveWordRepository.delete(sensitiveWordToDelete);
    }
    public void deleteSensitiveWord(String word){
        try{
            SensitiveWord sensitiveWordToDelete= getByWord(word);
            sensitiveWordRepository.delete(sensitiveWordToDelete);
        }catch (EntityNotFoundException E){
            throw new EntityNotFoundException();
        }
    }

}
