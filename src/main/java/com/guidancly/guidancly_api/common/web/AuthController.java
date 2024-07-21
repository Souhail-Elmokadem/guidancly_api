package com.guidancly.guidancly_api.common.web;


import com.guidancly.guidancly_api.common.dao.entities.SignIn;
import com.guidancly.guidancly_api.common.dao.entities.SignUp;
import com.guidancly.guidancly_api.common.services.AuthManager;
import com.guidancly.guidancly_api.guide.dao.entities.Guide;
import com.guidancly.guidancly_api.guide.dao.repositories.GuideRepository;
import com.guidancly.guidancly_api.guide.enums.GuideType;
import com.guidancly.guidancly_api.user.dao.entities.User;
import com.guidancly.guidancly_api.user.dao.repositories.UserRepository;
import com.guidancly.guidancly_api.user.enums.Role;
import com.guidancly.guidancly_api.visitor.dao.entities.Visitor;
import com.guidancly.guidancly_api.visitor.dao.repositories.VisitorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
@RequestMapping("/auth/api/v1")
@EnableMethodSecurity(prePostEnabled = true)
public class AuthController {


    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private JwtEncoder jwtEncoder;




    private JwtDecoder jwtDecoder;




    private UserDetailsService userDetailsService;



    private AuthenticationManager authenticationManager;

    JwtClaimsSet jwtClaimsSet,jwtClaimsSetRefresh;

    public AuthController(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder, UserDetailsService userDetailsService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, UserRepository userRepository, GuideRepository guideRepository, VisitorRepository visitorRepository, AuthManager authManager) {
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.guideRepository = guideRepository;
        this.visitorRepository = visitorRepository;
        this.authManager = authManager;
    }

    private PasswordEncoder passwordEncoder;

    private UserRepository userRepository;
    private GuideRepository guideRepository;
    private VisitorRepository visitorRepository;



    private AuthManager authManager;



    @GetMapping("/profile")
    public Authentication authentication(Authentication authentication){
        return authentication;
    }


    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> signup(@RequestBody SignUp request) {
        try {
            logger.info("Processing registration for user: {}", request.getEmail());

            Map<String, String> tokens = authManager.signUp(request);
            return ResponseEntity.ok(tokens);
        } catch (RuntimeException e) {
            logger.warn("Registration error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "User with the provided email or number already exists."));
        } catch (Exception e) {
            logger.error("Unexpected error during registration: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "An unexpected error occurred. Please try again later."));
        }
    }
    @PostMapping("/signin")
    public ResponseEntity<Map<String, String>> signIn(@RequestBody SignIn request) {
        try {
            logger.info("Attempting to authenticate user: {}", request.getEmail());
            Map<String, String> tokens = authManager.signIn(request);
            return ResponseEntity.ok(tokens);
        } catch (RuntimeException e) {
            logger.error("Authentication error: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid username or password."));
        } catch (Exception e) {
            logger.error("Unexpected error during authentication: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "An unexpected error occurred. Please try again later."));
        }
    }







}
