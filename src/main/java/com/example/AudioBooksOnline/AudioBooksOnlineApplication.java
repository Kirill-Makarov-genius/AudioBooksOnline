package com.example.AudioBooksOnline;

import com.example.AudioBooksOnline.config.StorageProperties;
import com.example.AudioBooksOnline.services.interfaces.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class AudioBooksOnlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(AudioBooksOnlineApplication.class, args);
	}


	@Bean
	CommandLineRunner init(StorageService storageService){
		return(args -> {
			storageService.init();
		});
	}
}
