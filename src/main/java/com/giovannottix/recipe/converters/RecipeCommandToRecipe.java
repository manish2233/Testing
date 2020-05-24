package com.giovannottix.recipe.converters;

import com.giovannottix.recipe.commands.CategoryCommand;
import com.giovannottix.recipe.commands.IngredientCommand;
import com.giovannottix.recipe.commands.RecipeCommand;
import com.giovannottix.recipe.domain.Category;
import com.giovannottix.recipe.domain.Ingredient;
import com.giovannottix.recipe.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author: Giovanni Esposito.
 * @Date : 05/20/20, Wed
 */
@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final CategoryCommandToCategory categoryCommandToCategory;
    private final NotesCommandToNotes notesCommandToNotes;

    public RecipeCommandToRecipe(IngredientCommandToIngredient ingredientCommandToIngredient,
                                 CategoryCommandToCategory categoryCommandToCategory,
                                 NotesCommandToNotes notesCommandToNotes) {
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.categoryCommandToCategory = categoryCommandToCategory;
        this.notesCommandToNotes = notesCommandToNotes;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {
        if (source == null) {
            return null;
        }

        return Recipe.builder().id(source.getId())
                .description(source.getDescription())
                .prepTime(source.getPrepTime())
                .cookTime(source.getCookTime())
                .serving(source.getServing())
                .source(source.getSource())
                .url(source.getUrl())
                .directions(source.getDirections())
                .difficulty(source.getDifficulty())
                .image(source.getImage())
                .note(notesCommandToNotes.convert(source.getNote()))
                .categories(getCategoriesFromCategoriesCommand(source.getCategories()))
                .ingredients(getIngredientsFromIngredientsCommand(source.getIngredients()))
                .build();
    }

    private Set<Category> getCategoriesFromCategoriesCommand(Set<CategoryCommand> categoryCommands) {
        Set<Category> categories = new HashSet<>();

        if (categoryCommands != null && categoryCommands.size() > 0) {
            categories = categoryCommands
                    .stream()
                    .map(categoryCommand -> categoryCommandToCategory.convert(categoryCommand))
                    .collect(Collectors.toSet());
        }

        return categories;
    }

    private Set<Ingredient> getIngredientsFromIngredientsCommand(Set<IngredientCommand> ingredientCommands) {
        Set<Ingredient> ingredients = new HashSet<>();

        if (ingredientCommands != null && ingredientCommands.size() > 0) {
            ingredients = ingredientCommands
                    .stream()
                    .map(ingredientCommand -> ingredientCommandToIngredient.convert(ingredientCommand))
                    .collect(Collectors.toSet());
        }

        return ingredients;
    }
}
