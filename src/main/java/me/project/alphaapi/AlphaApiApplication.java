package me.project.alphaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
public class AlphaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlphaApiApplication.class, args);
	}

}
