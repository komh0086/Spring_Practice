package com.example.spring_practice.Domain.Order.Repository;

import com.example.spring_practice.Domain.Order.Order;
import com.example.spring_practice.Domain.User.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findByDeliveryZip(String deliveryZip);//인터페이스가 메서드의 이름을 분석하여 용도를 파악해준다.

    List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);//pageable 객체에 맞는 User의 Order들을 PlacedAt의 내림차순으로 반환

}
