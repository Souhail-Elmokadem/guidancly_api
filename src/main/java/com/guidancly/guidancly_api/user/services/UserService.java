package com.guidancly.guidancly_api.user.services;

import com.guidancly.guidancly_api.user.dao.entities.User;
import com.guidancly.guidancly_api.user.dao.repositories.UserRepository;
import com.guidancly.guidancly_api.user.dto.UserDto;
import com.guidancly.guidancly_api.user.mappers.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class UserService implements UserManager{

    private UserRepository userRepository;
    private UserMapper userMapper;
    @Override
    public Collection<User> users() {
        return userRepository.findAll();
    }
    @Override
    public UserDto getUserByEmail(String email) {
        return userMapper.convertToDTO(userRepository.findByEmailOrNumber(email,email));
    }
}
