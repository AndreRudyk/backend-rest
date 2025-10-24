package com.example.records;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RecordsApiApplication {
    
    private static final Logger logger = LoggerFactory.getLogger(RecordsApiApplication.class);
    
    public static void main(String[] args) {
        try {
            ConfigurableApplicationContext context = SpringApplication.run(RecordsApiApplication.class, args);
            logger.info("Spring Boot API successfully started");
            logger.info("Database tables have been created/updated successfully");
            
            String port = context.getEnvironment().getProperty("server.port", "8080");
            String host = context.getEnvironment().getProperty("server.address", "0.0.0.0");
            logger.info("Application is running on http://{}:{}", host, port);
            
        } catch (Exception e) {
            logger.error("Failed to start application", e);
            System.exit(1);
        }
    }
}