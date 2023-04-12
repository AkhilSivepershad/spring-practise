package com.example.springpractise.service;

import com.example.springpractise.model.SensitiveWord;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MessageService {
    private final SensitiveWordService sensitiveWordService;
    public MessageService(SensitiveWordService sensitiveWordService ){
        this.sensitiveWordService=sensitiveWordService;
    }
    public String processMessage(String message){
        String outputString=message;
        StringTokenizer tokenizer = new StringTokenizer(message);
        List<String> messageList= new ArrayList<>();
        Set<String> uniqueWordsSet= new HashSet<>();

        while (tokenizer.hasMoreTokens()) {
            String currentWord = tokenizer.nextToken();
            uniqueWordsSet.add(currentWord);
            messageList.add(currentWord);
        }

        Optional<Set<SensitiveWord>> optionalUniqueSensitiveWordsSet=sensitiveWordService.getByListOfWords(uniqueWordsSet);
        if(optionalUniqueSensitiveWordsSet.isPresent()){
            outputString="";
            Set<SensitiveWord> uniqueSensitiveWordsSet=optionalUniqueSensitiveWordsSet.get();
            for (String word : messageList) {
                boolean found=false;
                for (SensitiveWord sensitiveWord : uniqueSensitiveWordsSet) {
                    if (word.equals(sensitiveWord.getWord())) {
                        found = true;
                        break;
                    }
                }
                if(found)
                    outputString+=makeStars(word);
                else
                    outputString+=" "+word;
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
