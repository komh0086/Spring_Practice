package com.example.spring_practice.Controller;

import com.example.spring_practice.Domain.Taco.Repository.TacoRepository;
import com.example.spring_practice.Domain.Taco.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping(path = "/design", produces="application/json")//produces="application/json"속성은 요청의 Accept 헤더에 application/json이 포함된 요청만을 DesignTacoController의 메서드에서 처라한다는 것을 나타냄
@RestController//view를 반환하는것이 아니고 HTTP응답으로 브라우저에 전달되어 나타남
@CrossOrigin(origins="*")
public class DesignTacoRESTController {

    private TacoRepository tacoRepository;

    @Autowired
    DesignTacoRESTController(TacoRepository tacoRepository){
        this.tacoRepository = tacoRepository;
    }

    @GetMapping(path = "/recent")
    public Iterable<Taco> recentTacos(){
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        return tacoRepository.findAll(page).getContent();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Taco> findTacoById(@PathVariable("id") Long id){
        Optional<Taco> optTaco = tacoRepository.findById(id);
        if(optTaco.isPresent()){
            return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco){//@RequestBody어노테이션을 사용하여 요청 body의 json객체가 Taco객체에 바인딩된다.
        return tacoRepository.save(taco);
    }
}