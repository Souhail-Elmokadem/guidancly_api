package com.guidancly.guidancly_api;

import com.guidancly.guidancly_api.guide.dao.entities.Guide;
import com.guidancly.guidancly_api.guide.dao.repositories.GuideRepository;
import com.guidancly.guidancly_api.guide.enums.GuideType;
import com.guidancly.guidancly_api.tour.dao.entities.Tour;
import com.guidancly.guidancly_api.tour.dao.repositories.TourRepository;
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
    CommandLineRunner start(UserRepository userRepository,
                            TourRepository tourRepository,
                            GuideRepository guideRepository){
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

            Guide guide = new Guide();
            guide.setFirstName("John");
            guide.setLastName("Doe");
            guide.setEmail("john.doe@example.com");
            guide.setNumber("1234567890");
            guide.setAvatar("avatar_url");
            guide.setPassword("password");
            guide.setRole(Role.GUIDE);
            guide.setCreatedAt(new Date());
            guide.setUpdatedAt(new Date());
            guide.setGuideType(GuideType.HISTORICAL);
            guideRepository.save(guide);

            Tour tour = new Tour();
            tour.setTitle("Historical Tour");
            tour.setDescription("A tour of historical places.");
            tour.setDepart("Main Square");
            tour.setDate(new Date());
            tour.setNumberOfVisitors(0);
            tour.setEstimatedFullTime("2 hours");
            tour.setGuide(guide);
            tourRepository.save(tour);

            System.out.println("Saved Guide: " + guide);
            System.out.println("Saved Tour: " + tour);





        };
    }
}
