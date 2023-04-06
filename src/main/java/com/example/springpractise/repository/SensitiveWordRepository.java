package com.example.springpractise.repository;

import com.example.springpractise.model.SensitiveWord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensitiveWordRepository extends CrudRepository<SensitiveWord, Long> {
    List<SensitiveWord> findByWord(String word);

}
