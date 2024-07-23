package com.guidancly.guidancly_api.user.dto;

import com.guidancly.guidancly_api.user.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String number;
    private String avatar;
    @Enumerated(EnumType.STRING)
    private Role role;
    private Date createdAt;
    private Date updatedAt;
}
