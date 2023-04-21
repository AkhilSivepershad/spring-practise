package com.example.springpractise.repository;

import com.example.springpractise.model.SensitiveWord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface SensitiveWordRepository extends CrudRepository<SensitiveWord, Long> {
    Optional<SensitiveWord> findByWordIgnoreCase(String word);

    Set<SensitiveWord> findAllByWordIgnoreCaseIn(Set<String> words);
}
