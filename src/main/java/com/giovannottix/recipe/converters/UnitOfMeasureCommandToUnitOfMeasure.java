package com.giovannottix.recipe.converters;

import com.giovannottix.recipe.commands.UnitOfMeasureCommand;
import com.giovannottix.recipe.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author: Giovanni Esposito.
 * @Date : 05/20/20, Wed
 */
@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand source) {
        if(source == null){
            return null;
        }

        return UnitOfMeasure.builder()
                .id(source.getId())
                .description(source.getDescription())
                .build();
    }
}
