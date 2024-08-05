package com.guidancly.guidancly_api.user.services;


import com.guidancly.guidancly_api.user.dao.entities.User;
import com.guidancly.guidancly_api.user.dto.UserDto;

import java.util.Collection;

public interface UserManager {

    Collection<User> users();

    UserDto getUserByEmail(String email);
}
