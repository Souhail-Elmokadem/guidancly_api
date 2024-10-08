package com.guidancly.guidancly_api.common.web;


import com.guidancly.guidancly_api.common.dao.entities.SignIn;
import com.guidancly.guidancly_api.common.dao.entities.SignUp;
import com.guidancly.guidancly_api.common.services.AuthManager;
import com.guidancly.guidancly_api.guide.dao.repositories.GuideRepository;
import com.guidancly.guidancly_api.user.dao.entities.User;
import com.guidancly.guidancly_api.user.dao.repositories.UserRepository;
import com.guidancly.guidancly_api.user.dto.UserDto;
import com.guidancly.guidancly_api.user.services.UserManager;
import com.guidancly.guidancly_api.visitor.dao.repositories.VisitorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
    private UserManager userManager;

    JwtClaimsSet jwtClaimsSet,jwtClaimsSetRefresh;

    public AuthController(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder, UserDetailsService userDetailsService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, UserRepository userRepository, GuideRepository guideRepository, VisitorRepository visitorRepository, AuthManager authManager,UserManager userManager) {
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.guideRepository = guideRepository;
        this.visitorRepository = visitorRepository;
        this.authManager = authManager;
        this.userManager=userManager;
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
            tokens.put("status", String.valueOf(HttpStatus.OK.value()));
            tokens.put("success", "true");
            tokens.put("message", "SignUp successful.");
            return ResponseEntity.ok(tokens);
        } catch (RuntimeException e) {
            logger.warn("Registration error: {}", e.getMessage());

            Map<String, String> response = new HashMap<>();
            response.put("status", String.valueOf(HttpStatus.UNAUTHORIZED.value()));
            response.put("success", "false");
            response.put("message", "User with this number or email already exist !");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);

        } catch (Exception e) {
            logger.error("Unexpected error during registration: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "An unexpected error occurred. Please try again later."));
        }
    }
    @PostMapping("/signin")
    public ResponseEntity<Map<String, String>> signIn(@RequestBody SignIn request) {
        try {
            Map<String, String> tokens = authManager.signIn(request);
            tokens.put("status",HttpStatus.OK.toString().substring(0,3));
            tokens.put("success", "true");
            tokens.put("message", "Login successful.");

            return ResponseEntity.ok(tokens);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("status",HttpStatus.BAD_REQUEST.toString().substring(0,3));
            response.put("success", "false");
            response.put("message", "Invalid credentials or authentication failed.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        }
    }
    @PostMapping("/signInWithNumber")
    public ResponseEntity<Map<String, String>> signInWithNumber(@RequestBody SignIn request) {
        try {

            Map<String, String> tokens = authManager.signInWithNumber(request);
            tokens.put("status",HttpStatus.OK.toString().substring(0,3));
            tokens.put("success", "true");
            tokens.put("message", "Login successful.");

            return ResponseEntity.ok(tokens);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("status",HttpStatus.BAD_REQUEST.toString().substring(0,3));
            response.put("success", "false");
            response.put("message", "Invalid credentials or authentication failed.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        }
    }
    @GetMapping("/userinfo")
    public ResponseEntity<Map<String, Object>> userinfo(@RequestHeader("Authorization") String token) {
        try {
            String jwtToken = token.replace("Bearer ", "");
            Jwt decodedToken = jwtDecoder.decode(jwtToken);
            Map<String, Object> claims = decodedToken.getClaims();
            UserDto userDto = userManager.getUserByEmail(claims.get("sub").toString());

            Map<String, Object> response = new HashMap<>();
            response.put("status", HttpStatus.OK.toString().substring(0, 3));
            response.put("success", "true");
            for (Map.Entry<String, Object> entry : claims.entrySet()) {
                response.put(entry.getKey(), entry.getValue().toString());
            }
            response.put("data",userDto);

            return ResponseEntity.ok(response);
        } catch (JwtException e) {
            logger.error("Invalid token: {}", e.getMessage());
            Map<String, Object> response = new HashMap<>();
            response.put("status", HttpStatus.UNAUTHORIZED.toString().substring(0, 3));
            response.put("success", "false");
            response.put("message", "Invalid or expired token.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        } catch (Exception e) {
            logger.error("Unexpected error while retrieving user info: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "An unexpected error occurred. Please try again later."));
        }
    }





}
