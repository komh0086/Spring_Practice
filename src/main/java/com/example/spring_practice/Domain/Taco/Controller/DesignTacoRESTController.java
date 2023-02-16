//package com.example.spring_practice.Domain.Taco.Controller;
//
//
//import com.example.spring_practice.Domain.Order.Order;
//import com.example.spring_practice.Domain.Order.Repository.OrderRepository;
//import com.example.spring_practice.Domain.Taco.Repository.TacoRepository;
//import com.example.spring_practice.Domain.Taco.Taco;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.hateoas.CollectionModel;
//import org.springframework.hateoas.Link;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
//
//@Slf4j
//@RequestMapping(path = "/design", produces="application/json")//produces="application/json"속성은 요청의 Accept 헤더에 application/json이 포함된 요청만을 DesignTacoController의 메서드에서 처라한다는 것을 나타냄
//@RestController//view를 반환하는것이 아니고 HTTP응답으로 브라우저에 전달되어 나타남
//@CrossOrigin(origins="*")
//public class DesignTacoRESTController {
//
//    private TacoRepository tacoRepository;
//
//    private OrderRepository orderRepository;
//
//    @Autowired
//    DesignTacoRESTController(TacoRepository tacoRepository, OrderRepository orderRepository){
//        this.tacoRepository = tacoRepository;
//        this.orderRepository = orderRepository;
//    }
//
//    @GetMapping(path = "/recent")//최근 타코 생성내역 반환
//    public CollectionModel<Taco> recentTacos(){
//
//        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
//        List<Taco> tacos = tacoRepository.findAll(page).getContent();
//
//        for(Taco taco:tacos){
//            Link selfLink = linkTo(DesignTacoRESTController.class).slash(taco.getId()).withSelfRel();
//            taco.add(selfLink);//각 타코 객체마다 하잍퍼링크를 추가해준다.
//        }
//        return CollectionModel.of(tacos);
//    }
//
//    @GetMapping(path = "/{id}")//해당 id에 해당하는 타코 반환
//    public ResponseEntity<Taco> findTacoById(@PathVariable("id") Long id){
//        Optional<Taco> optTaco = tacoRepository.findById(id);
//        log.info("   --- Designing taco REST");
//        if(optTaco.isPresent()){
//            return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
//        }else{
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @PostMapping(consumes="application/json")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Taco postTaco(@RequestBody Taco taco){//@RequestBody어노테이션을 사용하여 요청 body의 json객체가 Taco객체에 바인딩된다.
//        return tacoRepository.save(taco);
//    }
//
//    @PutMapping("/{orderId}")
//    public Order putOrder(@RequestBody Order order){
//        return orderRepository.save(order);
//    }
//
//    @PatchMapping(path = "/{orderId}", consumes="application/json")
//    public Order patchOrder(@PathVariable("orderId") Long orderId, @RequestBody Order patch){
//        Order order = orderRepository.findById(orderId).get();
//        if(patch.getDeliveryName() != null){
//            order.setDeliveryName(patch.getDeliveryName());
//        }
//        if(patch.getDeliveryCity() != null){
//            order.setDeliveryCity(patch.getDeliveryCity());
//        }
//        if(patch.getDeliveryState() != null){
//            order.setDeliveryState(patch.getDeliveryState());
//        }
//        if(patch.getDeliveryZip() != null){
//            order.setDeliveryZip(patch.getDeliveryZip());
//        }
//        if(patch.getDeliveryStreet() != null){
//            order.setDeliveryStreet(patch.getDeliveryStreet());
//        }
//        if(patch.getCcNumber() != null){
//            order.setCcNumber(patch.getCcNumber());
//        }
//        if(patch.getCcExpiration() != null){
//            order.setCcExpiration(patch.getCcExpiration());
//        }
//        if(patch.getCcCVV() != null){
//            order.setCcCVV(patch.getCcCVV());
//        }
//        return orderRepository.save(order);
//    }
//
//    @DeleteMapping("/{orderId}")
//    @ResponseStatus(code=HttpStatus.NO_CONTENT)
//    public void deleteOrder(@PathVariable("orderId") Long orderId){
//        try{
//            orderRepository.deleteById(orderId);
//        }catch(EmptyResultDataAccessException e){}//삭제할 주문이 없으면 EmptyResultDataAccessException이 에러를 받는다.
//    }
//}

//data-rest 의존성을 추가함으로써 REST API를 제공하는 컨트롤러는 자동으로 생성이 된다.
//그래서 기존의 컨트롤러가 없는 ingredients같은 도메인은 별 다른 컨트롤러 생성없이 REST API를 제공한다.