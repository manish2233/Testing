package com.giovannottix.recipe.services;

import com.giovannottix.recipe.commands.RecipeCommand;
import com.giovannottix.recipe.converters.RecipeCommandToRecipe;
import com.giovannottix.recipe.converters.RecipeToRecipeCommand;
import com.giovannottix.recipe.domain.Recipe;
import com.giovannottix.recipe.exceptions.NotFoundException;
import com.giovannottix.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author: Giovanni Esposito.
 * @Date : 05/10/20, Sun
 */
@Slf4j
@Service
@Transactional
public class RecipeServiceImpl implements RecipeService{

    private final RecipeRepository recipeRepository;

    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository,
                             RecipeCommandToRecipe recipeCommandToRecipe,
                             RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<RecipeCommand> getRecipes() {
        log.debug("Get recipes method in service");
        return getRecipesCommand(recipeRepository.findAll());
    }

    private Set<RecipeCommand> getRecipesCommand(Iterable<Recipe> recipes) {
        Set<RecipeCommand> recipeCommands = new HashSet<>();

        if(recipes != null){
            recipeCommands =
                    StreamSupport.stream(recipes.spliterator(), false)
                            .map(recipe -> recipeToRecipeCommand.convert(recipe))
                            .collect(Collectors.toSet());
        }

        return recipeCommands;
    }

    @Override
    public RecipeCommand getRecipesById(Long id) {

        Optional<Recipe> recipe = recipeRepository.findById(id);

        if (!recipe.isPresent()) {
            throw new NotFoundException("Recipe Not Found. Recipe ID: " + id);
        }

        return recipeToRecipeCommand.convert(recipe.orElse(null));
    }

    @Override
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
        final Recipe detachRecipe =
                recipeCommandToRecipe.convert(recipeCommand);

        final Recipe savedRecipe = recipeRepository.save(detachRecipe);
        log.debug("Saved Recipe with ID: - "+savedRecipe.getId());

        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    public void deleteRecipeById(Long idToDelete) {
        log.debug("Saved Recipe with ID: {0}", idToDelete);

        if(idToDelete == null) {
            return;
        }

        recipeRepository.deleteById(idToDelete);
    }
}
