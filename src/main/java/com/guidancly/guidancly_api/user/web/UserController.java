package com.guidancly.guidancly_api.user.web;

import com.guidancly.guidancly_api.user.dao.entities.User;
import com.guidancly.guidancly_api.user.services.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class UserController {

    @Autowired
    private UserManager userManager;
    @GetMapping("/users")

    public Collection<User> users(){
        return userManager.users();
    }

}
