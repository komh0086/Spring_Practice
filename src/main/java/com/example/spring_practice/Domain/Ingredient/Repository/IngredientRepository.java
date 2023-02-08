package com.example.spring_practice.Domain.Ingredient.Repository;

import com.example.spring_practice.Domain.Ingredient.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, String> {//기본적인 C(Create),R(Read), U(Update), D(Delete)를 제공해주는 Repository

}
