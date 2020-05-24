package com.giovannottix.recipe.domain;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: Giovanni Esposito.
 * @Date : 05/16/20, Sat
 */
class CategoryTest {

    Category category;

    @BeforeEach
    void setUp() {
        category =
                Category.builder()
                        .id(1L)
                        .description("Category")
                        .recipes(new HashSet<>())
                        .build();
    }

    @Test
    void getId() {
        assertEquals(1L, category.getId());
    }

    @Test
    void getDescription() {
        assertTrue("Category".equalsIgnoreCase(category.getDescription()));
    }

    @Test
    void getRecipes() {
        assertEquals(0, category.getRecipes().size());
    }
}