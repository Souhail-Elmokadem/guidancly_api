package com.guidancly.guidancly_api.common.services;

import com.guidancly.guidancly_api.user.dao.entities.User;
import com.guidancly.guidancly_api.user.dto.UserDto;
import com.guidancly.guidancly_api.user.services.UserManager;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class BaseImpl {

    private JwtEncoder jwtEncoder;
    private UserManager userManager;

    private JwtDecoder jwtDecoder;
    public UserDto getUserByToken(String token){
        String jwtToken = token.replace("Bearer ", "");
        Jwt decodedToken = jwtDecoder.decode(jwtToken);
        Map<String, Object> claims = decodedToken.getClaims();
        UserDto userDto = userManager.getUserByEmail(claims.get("sub").toString());
        return userDto;
    }
}
