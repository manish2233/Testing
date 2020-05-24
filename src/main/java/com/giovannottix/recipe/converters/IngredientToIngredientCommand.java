package com.giovannottix.recipe.converters;

import com.giovannottix.recipe.commands.IngredientCommand;
import com.giovannottix.recipe.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author: Giovanni Esposito.
 * @Date : 05/20/20, Wed
 */
@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommand uomCommandConverter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomCommandConverter) {
        this.uomCommandConverter = uomCommandConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient source) {
        if(source == null) {
            return null;
        }

        return IngredientCommand.builder().id(source.getId())
                .description(source.getDescription())
                .amount(source.getAmount())
                .recipeId(source.getRecipe() != null ?
                        source.getRecipe().getId() : null)
                .unitOfMeasure(uomCommandConverter.convert(source.getUnitOfMeasure()))
                .build();
    }
}
