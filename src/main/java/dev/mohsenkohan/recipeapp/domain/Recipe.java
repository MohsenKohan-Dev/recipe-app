package dev.mohsenkohan.recipeapp.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
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

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    @ManyToMany
    @JoinTable(
            name = "recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    public void setNotes(Notes notes) {
        notes.setRecipe(this);
        this.notes = notes;
    }

    public Recipe addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        ingredient.setRecipe(this);
        return this;
    }

    public void removeIngredient(Ingredient ingredient) {
        ingredients.remove(ingredient);
        ingredient.setRecipe(null);
    }
}
