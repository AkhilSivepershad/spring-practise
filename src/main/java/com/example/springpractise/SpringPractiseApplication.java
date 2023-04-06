package com.example.springpractise;

import com.example.springpractise.model.SensitiveWord;
import com.example.springpractise.repository.SensitiveWordRepository;
import com.example.springpractise.service.SensitiveWordService;
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
	public CommandLineRunner demo(SensitiveWordService service) {
		return (args) -> {
			System.out.println();
			service.createSensitiveWord(new SensitiveWord("LOOP"));

			System.out.println("SensitiveWords found with getSensitiveWords:");
			for (SensitiveWord sensitiveWordList : service.getSensitiveWords()) {
				System.out.println(sensitiveWordList.toString());
			}
			System.out.println("-------------------------------");

			System.out.println("SensitiveWords found with getByWord:");
			service.getByWord("SELECT").forEach(sensitiveWord -> {
				System.out.println(sensitiveWord.toString());
			});

			System.out.println();
			System.out.println("Updating a word:");
			service.getByWord("SELECT").forEach(sensitiveWord -> {
				sensitiveWord.setWord("NEW SELECT");
				service.updateSensitiveWord(sensitiveWord);
				service.getByWord("NEW SELECT").forEach(updatedsensitiveWord -> {
					System.out.println(updatedsensitiveWord.toString());
				});
			});

			System.out.println();
			System.out.println("All of the sensitive words after deleting:");
			service.getByWord("NEW SELECT").forEach(sensitiveWord -> {
				service.deleteSensitiveWord(sensitiveWord);
				for (SensitiveWord sensitiveWordList : service.getSensitiveWords()) {
					System.out.println(sensitiveWordList.toString());
				}
			});
		};
	}
}
