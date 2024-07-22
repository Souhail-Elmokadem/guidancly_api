package com.guidancly.guidancly_api.user.services;

import com.guidancly.guidancly_api.user.dao.entities.User;
import com.guidancly.guidancly_api.user.dao.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class UserService implements UserManager{

    private UserRepository userRepository;
    @Override
    public Collection<User> users() {
        return userRepository.findAll();
    }
}
