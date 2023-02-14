package com.example.spring_practice.Domain.Taco;

import com.example.spring_practice.Domain.Ingredient.Ingredient;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class Taco extends RepresentationModel<Taco> {//하이퍼링크를 추가해주기위해 RepresentationModel을 상속받음

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)//id를 자동으로 생성해주는 어노테이션
    private Long id;

   //@NotNull
    private Date createdAt;

    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;

    @ManyToMany(targetEntity = Ingredient.class)//Ingredient 객체와 ManyToMany로 매칭가능하도록 관계지정
    @Size(min=1, message="You must choose at least 1 ingredient")
    private List<Ingredient> ingredients;

    @PrePersist//메서드는 Taco 객체가 저장되기 전에 createdAt 속성을 초기화해주는데 사용
    void createdAt() {
        this.createdAt = new Date();
    }
}
