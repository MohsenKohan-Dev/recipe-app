package dev.mohsenkohan.recipeapp.controllers;

import dev.mohsenkohan.recipeapp.domain.Recipe;
import dev.mohsenkohan.recipeapp.services.ImageService;
import dev.mohsenkohan.recipeapp.services.RecipeService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageController {

    private final RecipeService recipeService;
    private final ImageService imageService;

    public ImageController(RecipeService recipeService, ImageService imageService) {
        this.recipeService = recipeService;
        this.imageService = imageService;
    }

    @GetMapping("/recipe/{id}/image")
    public String showUploadForm(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.findById(id));
        return "recipe/imageuploadform";
    }

    @PostMapping("/recipe/{id}/image")
    public String handleImagePost(@PathVariable Long id, @RequestParam("imagefile") MultipartFile file) {
        imageService.saveImageFile(id, file);
        return "redirect:/recipe/" + id + "/show";
    }

    @GetMapping(value = "recipe/{id}/recipeimage", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] renderImageFromDB(@PathVariable Long id) {
        Recipe recipe = recipeService.findById(id);

        byte[] image = recipe.getImage();

        return image;
    }
}
