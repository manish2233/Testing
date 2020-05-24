package com.giovannottix.recipe.services;

import com.giovannottix.recipe.commands.RecipeCommand;
import com.giovannottix.recipe.domain.Recipe;

import java.util.Set;

/**
 * @author: Giovanni Esposito.
 * @Date : 05/10/20, Sun
 */
public interface RecipeService {

    Set<RecipeCommand> getRecipes();

    RecipeCommand getRecipesById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

    void deleteRecipeById(Long idToDelete);
}
