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
public class NotesCommandToNotes implements Converter<NoteCommand, Note> {

    @Synchronized
    @Nullable
    @Override
    public Note convert(NoteCommand source) {
        if (source == null) {
            return null;
        }

        return Note.builder().id(source.getId())
                .recipeNotes(source.getRecipeNotes()).build();
    }
}
