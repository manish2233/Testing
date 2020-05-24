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
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final CategoryToCategoryCommand categoryToCategoryCommand;
    private final NotesToNotesCommand notesToNotesCommand;

    public RecipeToRecipeCommand(IngredientToIngredientCommand ingredientToIngredientCommand,
                                 CategoryToCategoryCommand categoryToCategoryCommand,
                                 NotesToNotesCommand notesToNotesCommand) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.categoryToCategoryCommand = categoryToCategoryCommand;
        this.notesToNotesCommand = notesToNotesCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {
        if (source == null) {
            return null;
        }

        return RecipeCommand.builder().id(source.getId())
                .description(source.getDescription())
                .prepTime(source.getPrepTime())
                .cookTime(source.getCookTime())
                .serving(source.getServing())
                .source(source.getSource())
                .url(source.getUrl())
                .image(source.getImage())
                .directions(source.getDirections())
                .difficulty(source.getDifficulty())
                .note(notesToNotesCommand.convert(source.getNote()))
                .categories(getCategoriesCommandFromCategories(source.getCategories()))
                .ingredients(getIngredientsCommandFromIngredients(source.getIngredients()))
                .build();
    }

    private Set<CategoryCommand> getCategoriesCommandFromCategories(Set<Category> categories) {
        Set<CategoryCommand> categoryCommands = new HashSet<>();

        if (categories != null && categories.size() > 0) {
            categoryCommands = categories
                    .stream()
                    .map(category -> categoryToCategoryCommand.convert(category))
                    .collect(Collectors.toSet());
        }

        return categoryCommands;
    }

    private Set<IngredientCommand> getIngredientsCommandFromIngredients(Set<Ingredient> ingredients) {
        Set<IngredientCommand> ingredientsCommand = new HashSet<>();

        if (ingredients != null && ingredients.size() > 0) {
            ingredientsCommand = ingredients
                    .stream()
                    .map(ingredientCommand -> ingredientToIngredientCommand.convert(ingredientCommand))
                    .collect(Collectors.toSet());
        }

        return ingredientsCommand;
    }
}
