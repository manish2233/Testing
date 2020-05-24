package com.giovannottix.recipe.services;

import com.giovannottix.recipe.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand findRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand command);

    void deleteById(Long recipeId, Long idToDelete);

}
