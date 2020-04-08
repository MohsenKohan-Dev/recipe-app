package dev.mohsenkohan.recipeapp.services;

import dev.mohsenkohan.recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long id);

    Recipe save(Recipe recipe);
}
