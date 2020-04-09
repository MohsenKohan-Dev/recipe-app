package dev.mohsenkohan.recipeapp.services;

import dev.mohsenkohan.recipeapp.domain.Ingredient;
import dev.mohsenkohan.recipeapp.domain.Recipe;
import dev.mohsenkohan.recipeapp.repositories.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient getIngredientOfRecipe(Recipe recipe, Long id) {
        return ingredientRepository.findAllByRecipeAndId(recipe, id);
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }
}
