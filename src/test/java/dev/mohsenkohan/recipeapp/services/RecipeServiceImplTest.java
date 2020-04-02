package dev.mohsenkohan.recipeapp.services;

import dev.mohsenkohan.recipeapp.domain.Recipe;
import dev.mohsenkohan.recipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    void getRecipes() {
        Recipe recipe = new Recipe();

        Set<Recipe> recipes = new HashSet<>();
        recipes.add(recipe);

        Mockito.when(recipeRepository.findAll()).thenReturn(recipes);

        Set<Recipe> allRecipes = recipeService.getRecipes();

        assertTrue(allRecipes.contains(recipe));

        Mockito.verify(recipeRepository, Mockito.times(1)).findAll();
    }
}