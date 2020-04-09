package dev.mohsenkohan.recipeapp.services;

import dev.mohsenkohan.recipeapp.domain.Recipe;
import dev.mohsenkohan.recipeapp.repositories.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    public final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void saveImageFile(Long recipeId, MultipartFile file) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow();

        try {

            recipe.setImage(file.getBytes());
            recipeRepository.save(recipe);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
