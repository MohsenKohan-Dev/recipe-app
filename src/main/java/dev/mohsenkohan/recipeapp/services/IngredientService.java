package dev.mohsenkohan.recipeapp.services;

import dev.mohsenkohan.recipeapp.domain.Ingredient;
import dev.mohsenkohan.recipeapp.domain.Recipe;

public interface IngredientService {

    Ingredient getIngredientOfRecipe(Recipe recipe, Long id);
}
