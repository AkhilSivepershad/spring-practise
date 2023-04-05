package com.example.springpractise.model;

import javax.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@ToString
//@NoArgsConstructor
public class SensitiveWord {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    //@Final
    private String word;

    protected SensitiveWord(){}
    public SensitiveWord(String word){
        this.word=word;
    }
}
