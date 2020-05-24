package com.giovannottix.recipe.services;

import com.giovannottix.recipe.commands.RecipeCommand;
import com.giovannottix.recipe.converters.RecipeCommandToRecipe;
import com.giovannottix.recipe.converters.RecipeToRecipeCommand;
import com.giovannottix.recipe.domain.Recipe;
import com.giovannottix.recipe.exceptions.NotFoundException;
import com.giovannottix.recipe.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author: Giovanni Esposito.
 * @Date : 05/16/20, Sat
 */
class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    Set<Recipe> recipeData;

    Recipe recipeFix;
    RecipeCommand recipeCommand;

    static final Long ID = 1L;
    static final String NEW_DESCRIPTION = "New Description";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository,
                recipeCommandToRecipe, recipeToRecipeCommand);

        Recipe recipe = new Recipe();
        recipeData = new HashSet<>();
        recipeData.add(recipe);

        recipeFix = Recipe.builder().id(ID).build();
        recipeCommand = RecipeCommand.builder().id(ID).build();

    }

    @Test
    void getRecipesTest() throws Exception {
        when(recipeRepository.findAll())
                .thenReturn(recipeData);

        when(recipeToRecipeCommand.convert(any(Recipe.class)))
                .thenReturn(recipeCommand);

        Set<RecipeCommand> recipes = recipeService.getRecipes();

        assertEquals(1, recipes.size());
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void getRecipeByIdFoundTest() throws Exception {

        when(recipeRepository.findById(anyLong()))
                .thenReturn(Optional.of(recipeFix));

        when(recipeToRecipeCommand.convert(any(Recipe.class)))
                .thenReturn(recipeCommand);

        RecipeCommand recipe = recipeService.getRecipesById(ID);

        assertNotNull(recipe);
        assertEquals(ID, recipe.getId());

        verify(recipeRepository, times(1)).findById(anyLong());
    }

    @Test
    public void getRecipeByIdNotFoundTest() throws Exception {
        when(recipeRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> recipeService.getRecipesById(ID));
        verify(recipeRepository, times(1)).findById(anyLong());
    }

    @Test
    public void deleteRecipeByIdNull() throws Exception {
        //Given
        Long idToDelete = null;

        //When
        recipeService.deleteRecipeById(idToDelete);

        //Then
        verifyNoInteractions(recipeRepository);
    }

    @Test
    public void deleteRecipeById() throws Exception {
        //Given
        Long idToDelete = 2L;

        //When
        recipeService.deleteRecipeById(idToDelete);

        //Then
        ArgumentCaptor<Long> argumentCaptor =
                ArgumentCaptor.forClass(Long.class);

        verify(recipeRepository, times(1))
                .deleteById(argumentCaptor.capture());
    }
}
