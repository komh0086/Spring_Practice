package com.example.spring_practice;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest//스프링 MVC 어플리케이션의 형태로 테스트가 실행되도록 해줌
public class HomeControllerTest {
    @Autowired
    private MockMvc mockMvc;//MockMvc 주입

    @Test
    public void testHomePage() throws Exception{
        mockMvc.perform(get("/"))//get 요청을 보냄
                .andExpect(status().isOk())//예상되는 결과가 HTTP 200이어야 함
                .andExpect(view().name("home"))//home이라는 View가 응답으로 와야 함
                .andExpect(content().string(
                        containsString("Welcome to ...")//컨텐츠안에 Welcome to ... 라는 문자열이 포함되어 있어야 함
                ));
    }
}
