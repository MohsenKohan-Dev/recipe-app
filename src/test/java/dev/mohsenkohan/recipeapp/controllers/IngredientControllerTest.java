package dev.mohsenkohan.recipeapp.controllers;

import dev.mohsenkohan.recipeapp.domain.Ingredient;
import dev.mohsenkohan.recipeapp.domain.Recipe;
import dev.mohsenkohan.recipeapp.services.IngredientService;
import dev.mohsenkohan.recipeapp.services.RecipeService;
import dev.mohsenkohan.recipeapp.services.UnitOfMeasureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class IngredientControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    IngredientService ingredientService;

    @Mock
    UnitOfMeasureService unitOfMeasureService;

    @InjectMocks
    IngredientController ingredientController;

    MockMvc mockMvc;

    Recipe mockRecipe;
    Ingredient mockIngredient;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();

        mockRecipe = new Recipe();
        mockRecipe.setId(1L);

        mockIngredient = new Ingredient();
        mockIngredient.setId(1L);
    }

    @Test
    void showIngredient() throws Exception {
        when(recipeService.findById(anyLong())).thenReturn(mockRecipe);
        when(ingredientService.getIngredientOfRecipe(any(), anyLong())).thenReturn(mockIngredient);

        mockMvc.perform(get("/recipe/1/ingredient/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/show"))
                .andExpect(model().attributeExists("ingredient"));
    }

    @Test
    void updateIngredient() throws Exception {
        when(recipeService.findById(anyLong())).thenReturn(mockRecipe);
        when(ingredientService.getIngredientOfRecipe(any(), anyLong())).thenReturn(mockIngredient);
        when(unitOfMeasureService.getAll()).thenReturn(anySet());

        mockMvc.perform(get("/recipe/1/ingredient/1/update"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe", "ingredient", "uomSet"))
                .andExpect(view().name("recipe/ingredient/ingredientform"));
    }

    @Test
    void createIngredient() throws Exception {
        when(recipeService.findById(anyLong())).thenReturn(mockRecipe);
        when(unitOfMeasureService.getAll()).thenReturn(anySet());

        mockMvc.perform(get("/recipe/1/ingredient/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe", "ingredient", "uomSet"))
                .andExpect(view().name("recipe/ingredient/ingredientform"));
    }
}