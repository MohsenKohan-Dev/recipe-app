package dev.mohsenkohan.recipeapp.controllers;

import dev.mohsenkohan.recipeapp.domain.Difficulty;
import dev.mohsenkohan.recipeapp.domain.Recipe;
import dev.mohsenkohan.recipeapp.exceptions.NotFoundException;
import dev.mohsenkohan.recipeapp.services.CategoryService;
import dev.mohsenkohan.recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
public class RecipeController {

    public static final String RECIPE_RECIPEFORM_URL = "recipe/recipeform";

    private final RecipeService recipeService;
    private final CategoryService categoryService;

    public RecipeController(RecipeService recipeService, CategoryService categoryService) {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/recipe/{id}/show", method = RequestMethod.GET)
    public String showById(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.findById(id));
        return "recipe/show";
    }

    @RequestMapping(path = "/recipe/new", method = RequestMethod.GET)
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("difficulties", Difficulty.values());
        model.addAttribute("categories", categoryService.findAll());
        return RECIPE_RECIPEFORM_URL;
    }

    @PostMapping("/recipe")
    public String createOrUpdate(@Valid @ModelAttribute Recipe recipe, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(
                    objectError -> log.debug(objectError.toString()));

            return RECIPE_RECIPEFORM_URL;
        }

        Recipe savedRecipe = recipeService.save(recipe);
        return "redirect:/recipe/" + savedRecipe.getId() + "/show";
    }

    @GetMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.findById(id));
        model.addAttribute("difficulties", Difficulty.values());
        model.addAttribute("categories", categoryService.findAll());
        return "recipe/recipeform";
    }

    @GetMapping("/recipe/{id}/delete")
    public String deleteRecipe(@PathVariable Long id) {
        recipeService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("recipe/{id}/ingredients")
    public String showIngredients(@PathVariable Long id, Model model) {
        Recipe recipe = recipeService.findById(id);
        model.addAttribute("recipe", recipe);
        return "recipe/ingredient/list";
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleNotFound(Exception exception) {
        ModelAndView modelAndView = new ModelAndView("404error");
        modelAndView.addObject("exception", exception);
        return modelAndView;
    }
}
