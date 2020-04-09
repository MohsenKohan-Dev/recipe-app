package dev.mohsenkohan.recipeapp.repositories;

import dev.mohsenkohan.recipeapp.domain.Ingredient;
import dev.mohsenkohan.recipeapp.domain.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

    Ingredient findAllByRecipeAndId(Recipe recipe, Long id);
}
