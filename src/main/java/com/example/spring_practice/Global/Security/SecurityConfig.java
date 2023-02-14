package com.example.spring_practice.Global.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
//        auth.ldapAuthentication()
//                .userSearchBase("ou=people")
//                .userSearchFilter("(uid={0}")
//                .groupSearchBase("ou=groups")
//                .groupSearchFilter("member={0}")
//                .passwordCompare()
//                .passwordEncoder(new NoEncodingPasswordEncoder())
//                .passwordAttribute("userPasscode");
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{//http 보안을 구성
        http
                .csrf().disable()//csrf 토큰을 사용하지 않음
                .authorizeHttpRequests()
                .requestMatchers("/design", "/orders").hasRole("USER")//design, orders 경로의 요청은 USER권한을 가진 사용자만 허용된다.
                .requestMatchers("/", "/**").permitAll()//모든 요청이 허용 된다.
                .and().headers().frameOptions().disable()//h2 console접근을 위해 사용했지만 원리는 잘 모르겠다.
                .and().formLogin().loginPage("/login")//로그인페이지 경로지정 인증이 필요할때 자동으로 이 경로로 보내준다.
                .and().logout().logoutSuccessUrl("/")
                .and().httpBasic();
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/h2-console/**");
    }

    @Bean
    public PasswordEncoder encoder(){
        return new NoEncodingPasswordEncoder();
    }



//    @Bean
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(userDetailsService)
//                .passwordEncoder(encoder());
//
//    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailService(){//사용자 인증정보 구성, 인메모리방식으로 변경이 필요 없는 경우 여기에 하드코딩한다.
//        UserDetails user1 = User.withDefaultPasswordEncoder()
//                .username("user3")
//                .password("1234")//나중에 암호화 필요
//                .roles("USER")
//                .build();
//        UserDetails user2 = User.withDefaultPasswordEncoder()
//                .username("user4")
//                .password("4321")
//                .roles("USER")
//                .build();
//        InMemoryUserDetailsManager res = new InMemoryUserDetailsManager(user1);
//        res.createUser(user2);
//        return res;
//    }
}
