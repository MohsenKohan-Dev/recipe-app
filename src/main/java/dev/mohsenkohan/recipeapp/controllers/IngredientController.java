package dev.mohsenkohan.recipeapp.controllers;

import dev.mohsenkohan.recipeapp.domain.Ingredient;
import dev.mohsenkohan.recipeapp.domain.Recipe;
import dev.mohsenkohan.recipeapp.domain.UnitOfMeasure;
import dev.mohsenkohan.recipeapp.services.IngredientService;
import dev.mohsenkohan.recipeapp.services.RecipeService;
import dev.mohsenkohan.recipeapp.services.UnitOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;

@Controller
public class IngredientController {

    private final IngredientService ingredientService;
    private final RecipeService recipeService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(IngredientService ingredientService, RecipeService recipeService, UnitOfMeasureService unitOfMeasureService) {
        this.ingredientService = ingredientService;
        this.recipeService = recipeService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{id}/show")
    public String showIngredient(@PathVariable Long recipeId, @PathVariable Long id, Model model) {
        Recipe recipe = recipeService.findById(recipeId);
        Ingredient ingredient = ingredientService.getIngredientOfRecipe(recipe, id);
        model.addAttribute("ingredient", ingredient);
        return "recipe/ingredient/show";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{id}/update")
    public String updateIngredient(@PathVariable Long recipeId, @PathVariable Long id, Model model) {
        Recipe recipe = recipeService.findById(recipeId);
        Ingredient ingredient = ingredientService.getIngredientOfRecipe(recipe, id);
        Set<UnitOfMeasure> unitOfMeasures = unitOfMeasureService.getAll();

        model.addAttribute("recipe", recipe);
        model.addAttribute("ingredient", ingredient);
        model.addAttribute("uomSet", unitOfMeasures);

        return "recipe/ingredient/ingredientform";
    }

    @PostMapping("/recipe/{id}/ingredient")
    public String createOrUpdate(@PathVariable Long id, @ModelAttribute Ingredient ingredient) {
        Ingredient i = ingredientService.save(ingredient);
        return "redirect:/recipe/" + id + "/ingredient/" + i.getId() + "/show";
    }

    @GetMapping("/recipe/{id}/ingredient/new")
    public String createIngredient(@PathVariable Long id, Model model) {
        Recipe recipe = recipeService.findById(id);

        Ingredient ingredient = new Ingredient();
        ingredient.setRecipe(recipe);

        Set<UnitOfMeasure> unitOfMeasures = unitOfMeasureService.getAll();

        model.addAttribute("recipe", recipe);
        model.addAttribute("ingredient", ingredient);
        model.addAttribute("uomSet", unitOfMeasures);

        return "recipe/ingredient/ingredientform";
    }
}
