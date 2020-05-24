package com.giovannottix.recipe.commands;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: Giovanni Esposito.
 * @Date : 05/20/20, Wed
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCommand {

    private Long id;

    @NotBlank
    @Size(min = 3, max = 255)
    private String description;

    private Set<RecipeCommand> recipes = new HashSet<>();
}
