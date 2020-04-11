package dev.mohsenkohan.recipeapp.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;

    @Lob
    private String directions;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @OneToMany(mappedBy = "recipe", cascade= CascadeType.ALL, orphanRemoval = true)
    private Set<Ingredient> ingredients = new HashSet<>();

    @Lob
    private byte[] image;

    @OneToOne(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Notes notes;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    public void setNotes(Notes notes) {
        this.notes = notes;
        notes.setRecipe(this);
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        ingredient.setRecipe(this);
    }

    public void removeIngredient(Ingredient ingredient) {
        ingredients.remove(ingredient);
        ingredient.setRecipe(null);
    }

    public void addCategory(Category category) {
        categories.add(category);
        category.getRecipes().add(this);
    }

    public void removeCategory(Category category) {
        categories.remove(category);
        category.getRecipes().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(description, recipe.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
