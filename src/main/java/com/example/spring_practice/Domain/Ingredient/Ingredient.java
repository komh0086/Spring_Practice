package com.example.spring_practice.Domain.Ingredient;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access= AccessLevel.PROTECTED, force = true)//JPA사용을 위한 annotation
@Entity//JPA사용을 위한 annotation
public class Ingredient {//타코의 식재료를 가리키는 도메인 클래스

    @Id//Table에서 primaryKey역할을 하는 Column
    private final String id;

    @NotNull
    private final String name;

    @NotNull
    private final Type type;

    public static enum Type{
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
