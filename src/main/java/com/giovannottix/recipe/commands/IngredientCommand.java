package com.giovannottix.recipe.commands;

import lombok.*;

import java.math.BigDecimal;

/**
 * @author: Giovanni Esposito.
 * @Date : 05/20/20, Wed
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngredientCommand {
    private Long id;
    private String description;
    private BigDecimal amount;
    private Long recipeId;
    private UnitOfMeasureCommand unitOfMeasure;
}
