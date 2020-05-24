package com.giovannottix.recipe.converters;

import com.giovannottix.recipe.commands.IngredientCommand;
import com.giovannottix.recipe.commands.UnitOfMeasureCommand;
import com.giovannottix.recipe.domain.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: Giovanni Esposito.
 * @Date : 05/20/20, Wed
 */
class IngredientCommandToIngredientTest {

    private static final Long ID_VALUE = 1L;
    private static final String DESCRIPTION = "ingredient";
    private static final BigDecimal AMOUNT = new BigDecimal(2);
    private static final Long UOM_ID = 2L;
    IngredientCommandToIngredient converter;

    @BeforeEach
    void setUp() {
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    void convert() {
        //Given
        UnitOfMeasureCommand unitOfMeasureCommand =
                UnitOfMeasureCommand.builder()
                        .id(UOM_ID)
                        .description(DESCRIPTION).build();

        IngredientCommand ingredientCommand = IngredientCommand.builder()
                .id(ID_VALUE)
                .description(DESCRIPTION)
                .amount(AMOUNT)
                .unitOfMeasure(unitOfMeasureCommand).build();

        //when
        Ingredient ingredient = converter.convert(ingredientCommand);

        //then
        assertNotNull(ingredient);
        assertNotNull(ingredient.getUnitOfMeasure());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(UOM_ID, ingredient.getUnitOfMeasure().getId());
    }

    @Test
    void covertWithNullUOM() {
        //Given
        IngredientCommand ingredientCommand = IngredientCommand.builder()
                .id(ID_VALUE)
                .description(DESCRIPTION)
                .amount(AMOUNT).build();

        //when
        Ingredient ingredient = converter.convert(ingredientCommand);

        //then
        assertNotNull(ingredient);
        assertNull(ingredient.getUnitOfMeasure());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
    }
}