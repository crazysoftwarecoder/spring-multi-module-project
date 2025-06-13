package com.example.repository.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
    "com.example.common.entity",
    "com.example.repository"
})
@EntityScan(basePackages = "com.example.common.entity")
@EnableJpaRepositories(basePackages = "com.example.repository")
public class RepositoryTestApplication {
    // This is a minimal Spring Boot application for testing
} 