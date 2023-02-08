package com.example.spring_practice.Domain.User;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {//fitness_potential에서 DTO같은 느낌이네
    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;

    public User toUser(PasswordEncoder passwordEncoder){
        return new User(username, passwordEncoder.encode(password), fullname, street, city, state, zip, phone);
        //User를 반환할때  Encoder를 통해 비밀번호를 암호화한 후에 반환한다.
    }
}
