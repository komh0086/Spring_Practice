package com.example.spring_practice;

import com.example.spring_practice.Domain.Ingredient.Ingredient;
import com.example.spring_practice.Domain.Ingredient.Repository.IngredientRepository;
import com.example.spring_practice.Domain.User.Repository.UserRepository;
import com.example.spring_practice.Domain.User.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringPracticeApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringPracticeApplication.class, args);
    }

    @Bean//어플리케이션이 시작되면서 실행하도록 Bean을 생성
    public CommandLineRunner dataLoader(IngredientRepository repo, UserRepository userRepository){
        return new CommandLineRunner(){
            @Override
            public void run(String... args) throws Exception{
                repo.save(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
                repo.save(new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP));
                repo.save(new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
                repo.save(new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN));
                repo.save(new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES));
                repo.save(new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES));
                repo.save(new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE));
                repo.save(new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE));
                repo.save(new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE));
                repo.save(new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE));
                userRepository.save(new User("1234", "1234", "fullname",
                        "street", "city", "state", "zip", "010-1234-5678"));
            }
        };
    }
}
