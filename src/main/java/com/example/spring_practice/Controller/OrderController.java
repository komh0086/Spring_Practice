package com.example.spring_practice.Controller;

import com.example.spring_practice.Domain.Order.Order;
import com.example.spring_practice.Domain.Order.Repository.OrderRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
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
    public String orderForm(){
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus){//@Valid어노테이션을 추가하고 Errors 객체를 파라미터로 추가해 준다.
        if(errors.hasErrors()){
            log.debug("validation error");
            return "orderForm";//에러가 있을경우 다시 design.html을 반환해 준다.
        }
        log.info("Order Submitted: " + order);
        orderRepository.save(order);
        sessionStatus.setComplete();//주문을 종료하면서 세션에 남아있는 객체들을 지우기위해 재설정
        return "redirect:/";
    }
}
