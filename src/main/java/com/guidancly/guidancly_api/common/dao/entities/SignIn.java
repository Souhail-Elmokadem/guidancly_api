package com.guidancly.guidancly_api.common.dao.entities;


import lombok.*;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SignIn {
    String email;
    String number;
    String password;
    String loginType;
    String refreshToken;

}
