package com.guidancly.guidancly_api.common.services;

import com.guidancly.guidancly_api.common.dao.entities.SignIn;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;

import com.guidancly.guidancly_api.common.dao.entities.SignUp;
import com.guidancly.guidancly_api.guide.dao.entities.Guide;
import com.guidancly.guidancly_api.guide.dao.repositories.GuideRepository;
import com.guidancly.guidancly_api.guide.enums.GuideType;
import com.guidancly.guidancly_api.user.dao.entities.User;
import com.guidancly.guidancly_api.user.dao.repositories.UserRepository;
import com.guidancly.guidancly_api.user.enums.Role;
import com.guidancly.guidancly_api.visitor.dao.entities.Visitor;
import com.guidancly.guidancly_api.visitor.dao.repositories.VisitorRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@Host
public class AuthService implements AuthManager{

    private JwtEncoder jwtEncoder;

    private JwtDecoder jwtDecoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final GuideRepository guideRepository;
    private final VisitorRepository visitorRepository;


    public AuthService( JwtDecoder jwtDecoder, AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtEncoder jwtEncoder1, PasswordEncoder passwordEncoder, UserRepository userRepository, GuideRepository guideRepository, VisitorRepository visitorRepository) {
        this.jwtDecoder = jwtDecoder;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtEncoder = jwtEncoder1;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.guideRepository = guideRepository;
        this.visitorRepository = visitorRepository;
    }

    @Override
    public Map<String, String> authenticate(String username, String password) {
        Authentication authentication = authenticateUser(username, password);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return generateTokens(userDetails);
    }

    @Override
    public Authentication authenticateUser(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    public Map<String, String> signUp(SignUp request) {
        if (userRepository.findByEmailOrNumber(request.getEmail(), request.getNumber()) != null) {
            throw new RuntimeException("User with the provided email or number already exists.");
        }

        User user = buildUserFromRequest(request);
        saveUserBasedOnRole(user, request.getRole());

        return generateTokens(user);
    }


    @Override
    public Map<String, String> signIn(SignIn loginRequest) throws AuthenticationException {
        Instant now = Instant.now();
        String subject = "";
        String scope = "";

        if ("pass".equals(loginRequest.getLoginType())) {

            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            UserDetails userDetails = (UserDetails) authenticate.getPrincipal();
            subject = userDetails.getUsername();


        } else if ("refreshToken".equals(loginRequest.getLoginType())) {
            Jwt jwtDecoded = jwtDecoder.decode(loginRequest.getRefreshToken());
            String email = jwtDecoded.getSubject();

            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            subject = userDetails.getUsername();
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(subject);

        return generateTokens(userDetails);
    }
    @Override
    public Map<String, String> signInWithNumber(SignIn loginRequest) throws AuthenticationException {

        String subject = "";
        String scope = "";

        if ("pass".equals(loginRequest.getLoginType())) {

            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getNumber(), loginRequest.getPassword()));

            UserDetails userDetails = (UserDetails) authenticate.getPrincipal();
            subject = userDetails.getUsername();


        } else if ("refreshToken".equals(loginRequest.getLoginType())) {
            Jwt jwtDecoded = jwtDecoder.decode(loginRequest.getRefreshToken());
            String email = jwtDecoded.getSubject();

            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            subject = userDetails.getUsername();
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(subject);

        return generateTokens(userDetails);
    }
    @Override
    public User buildUserFromRequest(SignUp request) {
        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .number(request.getNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .avatar(Host.hostname+"/content/logo.png")
                .role(request.getRole())
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
    }

    @Override
    public void saveUserBasedOnRole(User user, Role role) {
        if (role.equals(Role.GUIDE)) {
            Guide guide = createEntityFromUser(new Guide(), user);
            guide.setGuideType(GuideType.CULTURAL);
            guideRepository.save(guide);
        } else if (role.equals(Role.VISITOR)) {
            Visitor visitor = createEntityFromUser(new Visitor(), user);
            visitorRepository.save(visitor);
        }
    }

    @Override
    public <T extends User> T createEntityFromUser(T entity, User user) {
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setEmail(user.getEmail());
        entity.setNumber(user.getNumber());
        entity.setPassword(user.getPassword());
        entity.setAvatar(user.getAvatar());
        entity.setRole(user.getRole());
        entity.setCreatedAt(user.getCreatedAt());
        entity.setUpdatedAt(user.getUpdatedAt());
        return entity;
    }

    @Override
    public Map<String, String> generateTokens(UserDetails userDetails) {
        Map<String, String> tokens = new HashMap<>();
        Instant now = Instant.now();

        JwtClaimsSet accessClaims = JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(now.plus(Duration.ofMinutes(10)))
                .subject(userDetails.getUsername())
                .claim("scope", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" ")))
                .build();
        String accessToken = jwtEncoder.encode(JwtEncoderParameters.from(
                JwsHeader.with(MacAlgorithm.HS256).build(),
                accessClaims
        )).getTokenValue();
        tokens.put("access-token", accessToken);

        JwtClaimsSet refreshClaims = JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(now.plus(Duration.ofDays(30)))
                .subject(userDetails.getUsername())
                .build();
        String refreshToken = jwtEncoder.encode(JwtEncoderParameters.from(
                JwsHeader.with(MacAlgorithm.HS256).build(),
                refreshClaims
        )).getTokenValue();
        tokens.put("refresh-token", refreshToken);

        return tokens;
    }
}
