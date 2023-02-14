package com.example.spring_practice.Controller;

import com.example.spring_practice.Domain.Ingredient.Ingredient;
import com.example.spring_practice.Domain.Ingredient.Repository.IngredientRepository;
import com.example.spring_practice.Domain.Order.Order;
import com.example.spring_practice.Domain.Taco.Repository.TacoRepository;
import com.example.spring_practice.Domain.Taco.Taco;
import com.example.spring_practice.Domain.User.Repository.UserRepository;
import com.example.spring_practice.Domain.User.User;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j//자바에 사용하는 Simple Logging Facade라는 Logger를 사용하기 위한 어노테이션
@Controller
@SessionAttributes("order")//하나의 세션에서 동작하는 Taco와는 다르게 여러 요청을 통해 만들어지는 객체이므로 Session Scope에 저장하는 것이 좋다.
@RequestMapping(path = "/design")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;

    private final TacoRepository tacoRepository;

    private final UserRepository userRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository, UserRepository userRepository){
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
        this.userRepository = userRepository;
    }



    @GetMapping//"/design"경로로 Get요청이 들어오면 받는 컨트롤러
    public String showDesignForm(Model model, Principal principal) {//Principal은 우리가 인증을 마치고 Spring security로 보내는 객체의 최상위 인터페이스
        log.info("   --- Designing taco");
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(i -> ingredients.add(i));

        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }

        String username = principal.getName();//인증이 완료된 객체에서 이름을 가져오고
        User user = userRepository.findByUsername(username);//userRepository에서 이릉으로 User객체를 찾고
        model.addAttribute("user", user);//그 유저를 속성으로 넣어준뒤 반환한다.

        return "design";//모델 데이터를 브라우저에서 나타내는데 사용될 뷰의 논리적 이름
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "design")
    public Taco design() {
        return new Taco();
    }

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order){//@Valid어노테이션을 추가하고 Errors 객체를 파라미터로 추가해 준다.
        if(errors.hasErrors()){
            System.out.println(errors.toString());
            return "design";//에러가 있을경우 다시 design.html을 반환해 준다.
        }
        //이 부분에 POST요청을 처리한다.
        log.info("Processing design: " + design);

        Taco saved = tacoRepository.save(design);
        order.addDesign(saved);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type){
        return ingredients.stream()
                .filter(X -> X.getType().equals(type))
                .collect(Collectors.toList());
    }
}
