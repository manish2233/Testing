package com.giovannottix.recipe.converters;

import com.giovannottix.recipe.commands.NoteCommand;
import com.giovannottix.recipe.domain.Note;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author: Giovanni Esposito.
 * @Date : 05/20/20, Wed
 */
@Component
public class NotesToNotesCommand implements Converter<Note, NoteCommand> {

    @Synchronized
    @Nullable
    @Override
    public NoteCommand convert(Note source) {
        if(source == null) {
            return null;
        }

        return NoteCommand.builder().id(source.getId())
                .recipeNotes(source.getRecipeNotes()).build();
    }
}
