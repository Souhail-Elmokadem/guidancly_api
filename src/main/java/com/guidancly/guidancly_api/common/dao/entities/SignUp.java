package com.guidancly.guidancly_api.common.dao.entities;

import com.guidancly.guidancly_api.user.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SignUp {

    private String firstName;
    private String lastName;
    private String email;
    private String number;
    private String avatar;
    private Role role;
    private String password;
}
