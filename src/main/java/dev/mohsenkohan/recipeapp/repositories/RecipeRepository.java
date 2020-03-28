package dev.mohsenkohan.recipeapp.repositories;

import dev.mohsenkohan.recipeapp.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
