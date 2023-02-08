package com.example.spring_practice.Domain.User.Service;

import com.example.spring_practice.Domain.User.Repository.UserRepository;
import com.example.spring_practice.Domain.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserRepositoryUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;//자동 주입으로 초기화
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);//repository를 통해 user찾기
        if(user != null){
            return user;//user를 발견하면 해당 user를 반환
        }
        throw new UsernameNotFoundException("User '" + username + "' not found");//username으로 User를 찾지 못했을 경우에 에러를 던짐
    }
}
