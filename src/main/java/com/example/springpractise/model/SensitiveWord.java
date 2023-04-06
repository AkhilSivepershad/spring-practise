package com.example.springpractise.model;

import javax.persistence.*;

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
    //@Final
    private String word;
    private Timestamp createTime;
    public SensitiveWord(String word){
        this.word=word;
        this.createTime=new Timestamp(System.currentTimeMillis());
    }

    public void setWord(String word) {
        this.word = word;
        this.createTime=new Timestamp(System.currentTimeMillis());
    }
}
