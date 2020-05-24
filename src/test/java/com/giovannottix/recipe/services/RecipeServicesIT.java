package com.giovannottix.recipe.services;

import com.giovannottix.recipe.commands.RecipeCommand;
import com.giovannottix.recipe.converters.RecipeCommandToRecipe;
import com.giovannottix.recipe.converters.RecipeToRecipeCommand;
import com.giovannottix.recipe.domain.Recipe;
import com.giovannottix.recipe.repositories.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author: Giovanni Esposito.
 * @Date : 05/20/20, Wed
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RecipeServicesIT {
    public static final String NEW_DESCRIPTION = "New Description";

    @Autowired
    RecipeService recipeService;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Transactional
    @Test
    public void testSaveOfDescription() throws Exception {
        //given
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe testRecipe = recipes.iterator().next();
        RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);

        //when
        testRecipeCommand.setDescription(NEW_DESCRIPTION);
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(testRecipeCommand);

        //then
        assertEquals(NEW_DESCRIPTION, savedRecipeCommand.getDescription());
        assertEquals(testRecipe.getId(), savedRecipeCommand.getId());
        assertEquals(testRecipe.getCategories().size(),
                savedRecipeCommand.getCategories().size());
        assertEquals(testRecipe.getIngredients().size(),
                savedRecipeCommand.getIngredients().size());
    }
}
