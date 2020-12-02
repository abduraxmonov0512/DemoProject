package com.uzdemy.uzDemy.rest;

import com.uzdemy.uzDemy.dto.AuthRequestDto;
import com.uzdemy.uzDemy.model.User;
import com.uzdemy.uzDemy.security.jwt.JwtTokenProvider;
import com.uzdemy.uzDemy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.naming.AuthenticationException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/auth/login")
public class AuthenticationController {
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Map<Object, Object>> login(@RequestBody AuthRequestDto authRequestDto){
        System.out.println("HELLO WORLD ! ! ! ! ! ! !");
        try {
            String username = authRequestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, authRequestDto.getPassword()));
            User user = userService.findByEmail(username);
            System.out.println("test ");
            if(username == null){
                throw new UsernameNotFoundException("User with username: " + user + " not found");
            }
            System.out.println("test 1" );
            System.out.println(username +"test test test "+ user.getRoles());
            String token = jwtTokenProvider.createToken(username, user.getRoles());
            System.out.println("test 2" );
            System.out.println("test " + token);

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);

        }catch (Exception e){
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
