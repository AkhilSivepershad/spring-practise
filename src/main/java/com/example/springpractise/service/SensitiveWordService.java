package com.example.springpractise.service;

import com.example.springpractise.model.SensitiveWord;
import com.example.springpractise.repository.SensitiveWordRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
    @CacheEvict(value = {"all_words", "word","list_words"}, allEntries = true, beforeInvocation = true)
    public SensitiveWord  createSensitiveWord(String newWord){
        try {
           return sensitiveWordRepository.save(new SensitiveWord(newWord));
        }catch (Exception E){
            throw new IllegalArgumentException();
        }
    }
    @Cacheable("all_words")
    public List<SensitiveWord> getSensitiveWords() {
        Iterable<SensitiveWord> sensitiveWordIterable = sensitiveWordRepository.findAll();
        List<SensitiveWord> result = new ArrayList<>();
        sensitiveWordIterable.forEach(result::add);
        return result;
    }
    @Cacheable("word")
    public SensitiveWord getByWord(String word) {
        return sensitiveWordRepository.findByWord(word).orElseThrow(EntityNotFoundException::new);
    }
    @Cacheable("list_words")
    public Set<SensitiveWord> getByListOfWords(Set<String> words) {
        return sensitiveWordRepository.findAllByWordIn(words).orElseThrow(EntityNotFoundException::new);
    }
    @CacheEvict(value = {"all_words", "word","list_words"}, allEntries = true, beforeInvocation = true)
    public void deleteSensitiveWord(String word){
        try{
            SensitiveWord sensitiveWordToDelete= getByWord(word);
            sensitiveWordRepository.delete(sensitiveWordToDelete);
        }catch (EntityNotFoundException E){
            throw new EntityNotFoundException();
        }
    }

}
