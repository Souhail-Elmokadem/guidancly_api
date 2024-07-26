package com.guidancly.guidancly_api.common.services;

import com.guidancly.guidancly_api.common.dao.entities.SignIn;
import com.guidancly.guidancly_api.common.dao.entities.SignUp;
import com.guidancly.guidancly_api.guide.dao.repositories.GuideRepository;
import com.guidancly.guidancly_api.user.dao.entities.User;
import com.guidancly.guidancly_api.user.dao.repositories.UserRepository;
import com.guidancly.guidancly_api.user.enums.Role;
import com.guidancly.guidancly_api.visitor.dao.repositories.VisitorRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;

import java.util.Map;

public interface AuthManager {

    Map<String, String> authenticate(String username, String password);

    Authentication authenticateUser(String username, String password);

    Map<String, String> signUp(SignUp request);


    Map<String, String> signIn(SignIn request);

    Map<String, String> signInWithNumber(SignIn loginRequest) throws AuthenticationException;

    User buildUserFromRequest(SignUp request);

    void saveUserBasedOnRole(User user, Role role);

    <T extends User> T createEntityFromUser(T entity, User user);

    Map<String, String> generateTokens(UserDetails userDetails);
}
