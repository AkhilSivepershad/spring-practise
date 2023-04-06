package com.example.springpractise;

import com.example.springpractise.model.SensitiveWord;
import com.example.springpractise.repository.SensitiveWordRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringPractiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringPractiseApplication.class, args);

	}
	@Bean
	public CommandLineRunner demo(SensitiveWordRepository repository) {
		return (args) -> {
			System.out.println();
			repository.save(new SensitiveWord("LOOP"));

			System.out.println("SensitiveWords found with findAll():");
			for (SensitiveWord sensitiveWordList : repository.findAll()) {
				System.out.println(sensitiveWordList.toString());
			}
			System.out.println("-------------------------------");

			System.out.println("SensitiveWords found with findByWord:");
			repository.findByWord("SELECT").forEach(sensitiveWord -> {
				System.out.println(sensitiveWord.toString());
			});

		};
	}
}
