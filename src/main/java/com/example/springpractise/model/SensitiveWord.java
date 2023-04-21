package com.example.springpractise.model;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

import lombok.*;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class SensitiveWord {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(unique=true)
    private String word;
    private Timestamp create_time;
    public SensitiveWord(String word){
        this.word=word;
        this.create_time =new Timestamp(System.currentTimeMillis());
    }

    public void setWord(String word) {
        this.word = word;
        this.create_time =new Timestamp(System.currentTimeMillis());
    }
}
