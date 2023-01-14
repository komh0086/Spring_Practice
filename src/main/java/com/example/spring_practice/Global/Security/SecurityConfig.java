package com.example.spring_practice.Global.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{//http 보안을 구성
        http.authorizeHttpRequests()
                .requestMatchers("/design", "/orders")
                .hasRole("USER")
                .requestMatchers("/", "/**")
                .permitAll()
                .and()
                .httpBasic();
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailService(){//사용자 인증정보 구성, 인메모리방식으로 변경이 필요 없는 경우 여기에 하드코딩한다.
        UserDetails user1 = User.withDefaultPasswordEncoder()
                .username("user1")
                .password("1234")
                .roles("USER")
                .build();
        UserDetails user2 = User.withDefaultPasswordEncoder()
                .username("user1")
                .password("1234")//나중에 암호화 필요
                .roles("USER")
                .username("user2")
                .password("4321")//이렇게 하면 user1은 overwrite되어 사라짐
                .roles("USER")
                .build();
        InMemoryUserDetailsManager res = new InMemoryUserDetailsManager(user1);
        res.createUser(user2);
        return res;
    }
}
