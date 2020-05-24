package com.giovannottix.recipe.controllers;

import com.giovannottix.recipe.commands.RecipeCommand;
import com.giovannottix.recipe.domain.Recipe;
import com.giovannottix.recipe.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author: Giovanni Esposito.
 * @Date : 05/16/20, Sat
 */
class IndexControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    IndexController indexController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        indexController = new IndexController(recipeService);
    }

    /**
     * Test mocking MVC.
     *
     * @throws Exception
     */
    @Test
    void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void getIndex() {

        //given
        Set<RecipeCommand> recipes = new HashSet<>();
        recipes.add(RecipeCommand.builder().id(1L).build());
        recipes.add(RecipeCommand.builder().id(2L).build());

        when(recipeService.getRecipes()).thenReturn(recipes);

        //when
        String indexResponse = indexController.getIndex(model);

        ArgumentCaptor<Set<Recipe>> argumentCaptor =
                ArgumentCaptor.forClass(Set.class);

        //then
        assertTrue("index".equalsIgnoreCase(indexResponse));
        verify(recipeService,
                times(1)).getRecipes();
        verify(model,
                times(1))
                .addAttribute(matches("recipes"),
                        argumentCaptor.capture());

        Set<Recipe> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());
    }
}