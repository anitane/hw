package com.example.hw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;

@EnableJpaRepositories(basePackages = "com.example.hw.repostories")
@SpringBootApplication
public class HwApplication {

	public static void main(String[] args) {
		SpringApplication.run(HwApplication.class, args);
	}

}
