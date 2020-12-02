package com.uzdemy.uzDemy.security.jwt;

import com.uzdemy.uzDemy.model.Role;
import com.uzdemy.uzDemy.model.Status;
import com.uzdemy.uzDemy.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user){
        return new JwtUser(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getStatus().equals(Status.ACTIVE),
                user.getUpdated(),
                mapToGrandAuthority(user.getRoles())
        );
    }


    private static List<GrantedAuthority> mapToGrandAuthority(List<Role> userRoles){
        return userRoles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
