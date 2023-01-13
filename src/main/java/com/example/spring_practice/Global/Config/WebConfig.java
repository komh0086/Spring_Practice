package com.example.spring_practice.Global.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {//뷰 컨트롤러의 역할을 수행하는 구성 클래스

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");//"/" 경로로 GET 요청이 오면 home.html을 반환해 주는 ViewController를 추가해준다.
    }
}
