package com.guidancly.guidancly_api;

import com.guidancly.guidancly_api.user.dao.entities.User;
import com.guidancly.guidancly_api.user.dao.repositories.UserRepository;
import com.guidancly.guidancly_api.user.enums.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class GuidanclyApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuidanclyApiApplication.class, args);
    }

    @Bean
    CommandLineRunner start(UserRepository userRepository){
        return args -> {
//            User user = User.builder()
//                    .email("test")
//                    .avatar("jj")
//                    .createdAt(new Date())
//                    .role(Role.GUIDE)
//                    .password("123")
//                    .number("52")
//                    .firstName("ahmed")
//                    .lastName("ll")
//                    .build();
//            userRepository.save(user);
        };
    }
}
