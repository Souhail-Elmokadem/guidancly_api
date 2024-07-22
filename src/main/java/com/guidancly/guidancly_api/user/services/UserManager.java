package com.guidancly.guidancly_api.user.services;


import com.guidancly.guidancly_api.user.dao.entities.User;

import java.util.Collection;

public interface UserManager {

    Collection<User> users();
}
