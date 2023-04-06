package com.example.springpractise.service;

import com.example.springpractise.model.SensitiveWord;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.StringTokenizer;

@Service
public class MessageService {
    private final SensitiveWordService sensitiveWordService;
    public MessageService(SensitiveWordService sensitiveWordService ){
        this.sensitiveWordService=sensitiveWordService;
    }
    public String processMessage(String message){
        StringTokenizer tokenizer = new StringTokenizer(message);
        String outputString="";
        while (tokenizer.hasMoreTokens()) {
            String currentWord = tokenizer.nextToken();
            Optional<SensitiveWord> sensitiveWord= sensitiveWordService.getByWord(currentWord);
            if (sensitiveWord.isPresent()){
                outputString+=makeStars(currentWord);
            }
            else{
                outputString+=" "+currentWord;
            }
        }
        return outputString;
    }
    private String makeStars(String word){
        String outputString = " ";
        for (int i=0;i<word.length();i++){
            outputString+="*";
        }
        return outputString;
    }
}
