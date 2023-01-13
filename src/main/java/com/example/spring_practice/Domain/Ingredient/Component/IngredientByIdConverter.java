package com.example.spring_practice.Domain.Ingredient.Component;

import com.example.spring_practice.Domain.Ingredient.Ingredient;
import com.example.spring_practice.Domain.Ingredient.Repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import java.util.Optional;

public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientByIdConverter(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }

    @Override//String으로 ID가 들어오면 그에 맞는 Ingredient객체를 반환해주는 Converter
    public Ingredient convert(String id) {
        Optional<Ingredient> optionalIngredient = ingredientRepository.findById(id);
        return optionalIngredient.isPresent() ? optionalIngredient.get() : null;
    }
}
