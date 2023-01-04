package com.example.spring_practice.Controller;

import com.example.spring_practice.Model.Ingredient;
import com.example.spring_practice.Model.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j//자바에 사용하는 Simple Logging Facade라는 Logger를 사용하기 위한 어노테이션
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    @GetMapping//"/design"경로로 Get요청이 들어오면 받는 컨트롤러
    public String showDesignForm(Model model){
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),//나중에 DB추가하면 삭제할거 하드코딩한 부분
                new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE)
        );
        Ingredient.Type[] types = Ingredient.Type.values();
        for(Ingredient.Type type : types){
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }

        model.addAttribute("taco", new Taco());
        return "design";//모델 데이터를 브라우저에서 나타내는데 사용될 뷰의 논리적 이름
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type){
        return ingredients.stream()
                .filter(X -> X.getType().equals(type))
                .collect(Collectors.toList());
    }
}
