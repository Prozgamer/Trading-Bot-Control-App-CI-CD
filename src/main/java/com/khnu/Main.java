package com.khnu;

import com.khnu.model.AppConfigurations; // Імпортуй свій клас конфігурації
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppConfigurations.class) // <--- ДОДАЙ ЦЕ ТУТ
public class Main {
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}