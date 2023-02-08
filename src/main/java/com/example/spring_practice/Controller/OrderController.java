package com.example.spring_practice.Controller;

import com.example.spring_practice.Domain.Order.Order;
import com.example.spring_practice.Domain.Order.Repository.OrderRepository;
import com.example.spring_practice.Domain.User.User;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@SessionAttributes("order")
@RequestMapping("/orders")
public class OrderController {

    private OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String orderForm(@AuthenticationPrincipal User user, @ModelAttribute Order order){
        if(order.getDeliveryName() == null){
            order.setDeliveryName(user.getFullname());
        }
        if(order.getDeliveryStreet() == null){
            order.setDeliveryStreet(user.getStreet());
        }
        if(order.getDeliveryCity() == null){
            order.setDeliveryCity(user.getCity());
        }
        if(order.getDeliveryState() == null){
            order.setDeliveryState(user.getState());
        }
        if(order.getDeliveryZip() == null){
            order.setDeliveryZip(user.getZip());
        }//해당사용자의 이름과 주소를 Order객체의 각 속성에 설정하여 처음 Get요청이 들어왔을때 주문 폼에 사용자의 정보가 적혀있을 것이다.
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal User user){//@Valid어노테이션을 추가하고 Errors 객체를 파라미터로 추가해 준다.
        if(errors.hasErrors()){
            log.debug("validation error");
            return "orderForm";//에러가 있을경우 다시 design.html을 반환해 준다.
        }
        order.setUser(user);//어떤 user의 주문인지 알수 있다.
        log.info("Order Submitted: " + order);
        orderRepository.save(order);
        sessionStatus.setComplete();//주문을 종료하면서 세션에 남아있는 객체들을 지우기위해 재설정
        return "redirect:/";
    }
}
