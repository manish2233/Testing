package com.giovannottix.recipe.controllers;

import com.giovannottix.recipe.commands.RecipeCommand;
import com.giovannottix.recipe.exceptions.NotFoundException;
import com.giovannottix.recipe.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsInstanceOf.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author: Giovanni Esposito.
 * @Date : 05/18/20, Mon
 */
public class RecipeControllerTest {

    @Mock
    RecipeService recipeService;

    RecipeController recipeController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        recipeController = new RecipeController(recipeService);

        mockMvc =
                MockMvcBuilders.standaloneSetup(recipeController)
                        .setControllerAdvice(new ControllerExceptionHandler())
                        .build();

    }

    @Test
    public void getRecipeDetailTest() throws Exception {
        RecipeCommand recipe = RecipeCommand.builder().id(1L).build();

        when(recipeService.getRecipesById(anyLong())).thenReturn(recipe);

        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"))
                .andExpect(model().attribute("recipe", equalTo(recipe)));
    }

    @Test
    public void newRecipeTest() throws Exception {
        mockMvc.perform(get("/recipe/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform"))
                .andExpect(model().attribute("recipe",
                        any(RecipeCommand.class)));
    }

    @Test
    public void saveOrUpdateRecipeTest() throws Exception {
        RecipeCommand recipeSent =
                RecipeCommand.builder().description("Description").build();
        RecipeCommand recipeSaved =
                RecipeCommand.builder().id(1L).description("Description").build();

        when(recipeService.saveRecipeCommand(ArgumentMatchers.any())).thenReturn(recipeSaved);

        mockMvc.perform(post("/recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("description", "Description")
                .param("directions", "Directions")
                .param("id", ""))
                .andExpect(redirectedUrl("/recipe/1/show"));
    }

    @Test
    public void updateRecipeTest() throws Exception {
        RecipeCommand recipe = RecipeCommand.builder().id(1L).build();

        when(recipeService.getRecipesById(anyLong())).thenReturn(recipe);

        mockMvc.perform(get("/recipe/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform"))
                .andExpect(model().attribute("recipe", equalTo(recipe)));
    }


    @Test
    public void deleteRecipeByIdTest() throws Exception {
        doNothing().when(recipeService).deleteRecipeById(anyLong());

        mockMvc.perform(get("/recipe/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(recipeService, times(1))
                .deleteRecipeById(anyLong());
    }

    @Test
    public void testGetRecipeNotFound() throws Exception {

        when(recipeService.getRecipesById(anyLong()))
                .thenThrow(NotFoundException.class);

        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("404error"));
    }

    @Test
    public void testGetRecipeArgumentError() throws Exception {
        mockMvc.perform(get("/recipe/asd/show"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("400error"));
    }

}
