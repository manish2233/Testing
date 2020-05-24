package com.giovannottix.recipe.commands;

import lombok.*;

/**
 * @author: Giovanni Esposito.
 * @Date : 05/20/20, Wed
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnitOfMeasureCommand {
    private Long id;
    private String description;
}
