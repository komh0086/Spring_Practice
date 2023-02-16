package com.example.spring_practice.Domain.Taco.Controller;

import com.example.spring_practice.Domain.Taco.Repository.TacoRepository;
import com.example.spring_practice.Domain.Taco.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController//application.yml에서 지정한 rest api경로를 엔드포인트로 가짐
public class RecentTacosController {
    private TacoRepository tacoRepository;

    public RecentTacosController(TacoRepository tacoRepository){
        this.tacoRepository = tacoRepository;
    }

    @GetMapping(path = "/tacos", produces="application/hal+json")
    @ResponseBody
    public ResponseEntity<Taco> recentTacos(){
        log.info("get recent tacos");
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        List<Taco> tacos = tacoRepository.findAll(page).getContent();
        Taco taco = tacos.get(0);
        return new ResponseEntity<>(taco, HttpStatus.OK);
    }
}
