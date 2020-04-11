package dev.mohsenkohan.recipeapp.services;

import dev.mohsenkohan.recipeapp.domain.Category;

import java.util.Set;

public interface CategoryService {

    Set<Category> findAll();
}
