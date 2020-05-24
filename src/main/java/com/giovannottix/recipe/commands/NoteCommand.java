package com.giovannottix.recipe.commands;

import lombok.*;

/**
 * @author: Giovanni Esposito.
 * @Date : 05/20/20, Wed
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteCommand {
    private Long id;
    private RecipeCommand recipe;
    private String recipeNotes;
}
