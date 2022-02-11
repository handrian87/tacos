package t.spring.tacos.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import t.spring.tacos.model.Ingredient;
import t.spring.tacos.model.Order;
import t.spring.tacos.model.Taco;
import t.spring.tacos.repo.IngredientRepository;
import t.spring.tacos.repo.TacoRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
    private final IngredientRepository ingredientRepository;
    private TacoRepository tacoRepository;

    @ModelAttribute(name="order")
    public Order order(){
        return new Order();
    }

    @ModelAttribute(name="taco")
    public Taco taco(){
        return new Taco();
    }

    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository){
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }

    @GetMapping
    public String showDesignForm(Model model) {
//        List<Ingredient> ingredients = Arrays.asList(
//                new Ingredient("FLTO","Flour Tortilla", Ingredient.Type.WRAP),
//                new Ingredient("COTO","Corn Tortilla", Ingredient.Type.WRAP),
//                new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
//                new Ingredient("CARN","Carnitas", Ingredient.Type.WRAP),
//                new Ingredient("TMTO","Diced Tomatoes", Ingredient.Type.VEGGIES),
//                new Ingredient("LETC","Lettuce", Ingredient.Type.VEGGIES),
//                new Ingredient("CHED","Cheddar", Ingredient.Type.CHEESE),
//                new Ingredient("JACK","Monterrey Jack", Ingredient.Type.CHEESE),
//                new Ingredient("SLSA","Salsa", Ingredient.Type.SAUCE),
//                new Ingredient("SRCR","Sour Cream", Ingredient.Type.SAUCE)
//        );
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(i -> ingredients.add(i));

        Ingredient.Type[] types = Ingredient.Type.values();
        for(Ingredient.Type type: types){
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
        return "design";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    public String processDesign(@Valid Taco tacoToSave, Errors errors, @ModelAttribute Order order){
        if(errors.hasErrors()){
            return "design";
        }
        Taco saved = tacoRepository.save(tacoToSave);
        //order.addDesign(saved);
        //log.info("Processing design:" + design);
        return "redirect:/orders/current";
    }
}
