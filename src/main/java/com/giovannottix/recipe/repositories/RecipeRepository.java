package com.giovannottix.recipe.repositories;

import com.giovannottix.recipe.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * @author: Giovanni Esposito.
 * @Date : 05/10/20, Sun
 */
public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
