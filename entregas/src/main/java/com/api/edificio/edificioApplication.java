package com.api.edificio;

import org.springframework.boot.autoconfigure.domain.EntityScan; 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { 
    "com.api.thymeleaf", 
    "com.api.controller", 
    "com.api.repository",
    "com.api.edificio",
    "com.api.config",
    "com.api.service"
})
@EnableJpaRepositories("com.api.repository")
@EntityScan("com.api.thymeleaf") 

public class edificioApplication {
    public static void main(String[] args) {
        SpringApplication.run(edificioApplication.class, args);
    }
}
