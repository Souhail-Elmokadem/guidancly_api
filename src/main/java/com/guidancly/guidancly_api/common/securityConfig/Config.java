package com.guidancly.guidancly_api.common.securityConfig;

import com.guidancly.guidancly_api.user.dao.repositories.UserRepository;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;


@Configuration
@EnableWebSecurity
public class Config {


    private String secretKey="MIICXQIBAAKBgQCUZt6MM7A7sB13b3NBGEMLUJfhpYngU/hiUTWL+MDgjtdR/cLm\n" +
            "wvOQPEDQYfaH7a9mP7qvbAGmaryGmDh19PC5ZqMY+cdBlg3v2UtKQXtsbCLxRswW\n" +
            "6JqH62x6X+a+TzDaFZ2yia42Nq4fiC11s9qd37akN70lxCtpI8pvYWMusQIDAQAB\n" +
            "AoGAQOJRP1+CGDbCS2wRKk7fUV0lauUjyU8tdF8pWE+klQofHpwi0R4/u7p8ZB/Q\n" +
            "2FGVtND8YKKKfiaA0yBO31P+w4PTxRLIHwydEuIWQQYwD0HdI/vbX9zZ9bYoY+Zp\n" +
            "ZNuMrCboiRvSwWPHMMZ1C3Z88GmQtWY91Hmc1ccjO6RcFyECQQDp2wjycaXWXZlI\n" +
            "8MjARYkdBH7RMLkfOa4ZYtsuZuwdnySOY5LbgOARFtSieA26mxAPxnpxnhKxDgCu\n" +
            "ae5RvANFAkEAonRSzDuqQFcEybNorn9Cp8KkDZVCBGYrrKNg6jqNTXvXRHb3IkTg\n" +
            "TNxHAVEcm+sQdhFxfTCLGfbxYk1p7N2efQJBAJ527xwiSKb269zSVKZ9OAkyt6CX\n" +
            "LIptMn0/UJij45G6+jGGZchaDvtlhhbF1T/CvWKDMwt0euQBQhLt8zDtfyUCQE3F\n" +
            "PjYDgjeW8PXWa5DN340MQxk1kQsmFlZpSGYZbbfgR2fBRAIl0vM4qM1alUWXPKQp\n" +
            "KZly+cSOUXRr9v+sAMkCQQC+Jl0xUK7liznmaojTvkNh9Z6InH3jbWIG5ErVTB3/\n" +
            "bOPj0D6llu0N2iUSQUWo1qkLYxnhKyZn2+m4BWR0ypwq";



    private UserRepository userRepository;

    public Config(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(c->c.disable())
                //.httpBasic(Customizer.withDefaults())
                .cors(Customizer.withDefaults())
                .oauth2ResourceServer(ao->ao.jwt(Customizer.withDefaults()))
                .authorizeHttpRequests(ar->ar.requestMatchers("/auth/**").permitAll())
                .authorizeHttpRequests(ar-> ar.requestMatchers("/content/**").permitAll())

                .authorizeHttpRequests(ar->ar.anyRequest().authenticated())
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            com.guidancly.guidancly_api.user.dao.entities.User user = userRepository.findByEmailOrNumber(username,username);
            if (user == null) {
                throw new UsernameNotFoundException("Utilisateur non trouvé avec l'email d'utilisateur : " + username);
            }
            return User.withUsername(user.getEmail())
                    .password(user.getPassword())
                    .authorities(user.getRole().toString())
                    .build();
        };
    }
    @Bean
    JwtEncoder jwtEncoder(){
        return new NimbusJwtEncoder(new ImmutableSecret<>(secretKey.getBytes()));
    }
    @Bean
    JwtDecoder jwtDecoder(){
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(),"RSA");
        return NimbusJwtDecoder.withSecretKey(secretKeySpec).macAlgorithm(MacAlgorithm.HS256).build();

    }
    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return new ProviderManager(daoAuthenticationProvider);
    }

    // methode pour allowed hosts

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);
        return source;
    }

}
