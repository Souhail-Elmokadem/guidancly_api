package com.guidancly.guidancly_api.user.mappers;

import com.guidancly.guidancly_api.user.dao.entities.User;
import com.guidancly.guidancly_api.user.dto.UserDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserMapperImpl implements  UserMapper{
    private ModelMapper modelMapper;
    @Override
    public UserDto convertToDTO(User user) {
        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public User convertFromDTO(UserDto userDto) {
        return modelMapper.map(userDto,User.class);
    }
}
