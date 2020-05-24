package com.giovannottix.recipe.converters;

import com.giovannottix.recipe.commands.CategoryCommand;
import com.giovannottix.recipe.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author: Giovanni Esposito.
 * @Date : 05/20/20, Wed
 */
@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category>{

    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand source) {
        if(source == null) {
            return null;
        }

        return Category.builder()
                .id(source.getId())
                .description(source.getDescription()).build();
    }
}
