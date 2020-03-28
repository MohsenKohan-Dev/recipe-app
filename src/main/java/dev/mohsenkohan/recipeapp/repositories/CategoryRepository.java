package dev.mohsenkohan.recipeapp.repositories;

import dev.mohsenkohan.recipeapp.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
