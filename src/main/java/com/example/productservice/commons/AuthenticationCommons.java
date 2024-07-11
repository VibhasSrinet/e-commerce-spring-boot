package com.example.productservice.commons;

import com.example.productservice.dtos.UserDto;
import com.example.productservice.inheritancedemo.joinedtable.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationCommons {
    private RestTemplate restTemplate;

    public  AuthenticationCommons(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public UserDto validateToken(String token){
        try {
            ResponseEntity<UserDto> userDto= restTemplate.postForEntity("http://localhost:8181/users/validate/"+token, null, UserDto.class);
            if(userDto.getStatusCode().is2xxSuccessful()){
                return userDto.getBody();
            }
        } catch (RestClientException e) {
            System.err.println("RestClientException: " + e.getMessage());
        }
        return null;
    }
}
