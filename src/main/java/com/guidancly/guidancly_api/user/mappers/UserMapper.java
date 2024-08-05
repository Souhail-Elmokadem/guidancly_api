package com.guidancly.guidancly_api.user.mappers;


import com.guidancly.guidancly_api.user.dao.entities.User;
import com.guidancly.guidancly_api.user.dto.UserDto;

public interface UserMapper {

    UserDto convertToDTO(User user);
    User convertFromDTO(UserDto userDto);

}
