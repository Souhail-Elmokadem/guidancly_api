package com.guidancly.guidancly_api;

import com.guidancly.guidancly_api.common.dao.entities.Preferences;
import com.guidancly.guidancly_api.guide.dao.entities.Guide;
import com.guidancly.guidancly_api.guide.dao.repositories.GuideRepository;
import com.guidancly.guidancly_api.guide.enums.GuideType;
import com.guidancly.guidancly_api.tour.dao.entities.Tour;
import com.guidancly.guidancly_api.tour.dao.repositories.TourRepository;
import com.guidancly.guidancly_api.user.dao.entities.User;
import com.guidancly.guidancly_api.user.dao.repositories.UserRepository;
import com.guidancly.guidancly_api.user.enums.Role;
import com.guidancly.guidancly_api.visitor.dao.entities.Visitor;
import com.guidancly.guidancly_api.visitor.dao.repositories.VisitorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class GuidanclyApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuidanclyApiApplication.class, args);
    }

    @Bean
    CommandLineRunner start(UserRepository userRepository,
                            TourRepository tourRepository,
                            GuideRepository guideRepository,
                            VisitorRepository visitorRepository){
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

            Visitor visitor_ = new Visitor();
            visitor_.setFirstName("Visitor Apah");
            visitor_.setLastName("LastNameApp");
            visitor_.setEmail("visitor@example.com");
            visitor_.setNumber("000000");
            visitor_.setAvatar("avatar_url");
            visitor_.setPassword("password");
            visitor_.setRole(Role.VISITOR);
            visitor_.setCreatedAt(new Date());
            visitor_.setUpdatedAt(new Date());
            visitor_.setPreferences(null);
            visitor_.setCurrentTour(null);

            Tour tour = new Tour();
            tour.setTitle("Historical Tour");
            tour.setDescription("A tour of historical places.");
            tour.setDepart("Main Square");
            tour.setDate(new Date());
            List<Visitor> visitors = new ArrayList<>();
            visitors.add(visitorRepository.save(visitor_));
            tour.setVisitors(visitors);
            tour.setNumberOfVisitors(0);
            tour.setEstimatedFullTime("2 hours");
            tour.setGuide(guide);
            Tour savedTour = tourRepository.save(tour);

            visitor_.setCurrentTour(savedTour);
            visitorRepository.save(visitor_);

            // Adding more visitors
            for (int i = 1; i <= 4; i++) {
                Visitor visitor = new Visitor();
                visitor.setFirstName("Visitor" + i);
                visitor.setLastName("LastName" + i);
                visitor.setEmail("visitor" + i + "@example.com");
                visitor.setNumber("000000000" + i);
                visitor.setAvatar("avatar_url" + i);
                visitor.setPassword("password" + i);
                visitor.setRole(Role.VISITOR);
                visitor.setCreatedAt(new Date());
                visitor.setUpdatedAt(new Date());
                visitor.setCurrentTour(savedTour);
                visitors.add(visitorRepository.save(visitor));
            }

            savedTour.setNumberOfVisitors(visitors.size());
            tourRepository.save(savedTour);

            System.out.println("Saved Guide: " + guide);
            System.out.println("Saved Tour: " + savedTour);
            System.out.println("Saved Visitors: " + savedTour.getVisitors());
        };
    }
}
