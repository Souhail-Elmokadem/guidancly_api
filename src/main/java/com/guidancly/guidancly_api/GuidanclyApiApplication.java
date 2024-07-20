package com.guidancly.guidancly_api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GuidanclyApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuidanclyApiApplication.class, args);
    }

    @Bean
    CommandLineRunner start(){
        return args -> {

        };
    }
}
