package com.giovannottix.recipe.converters;

import com.giovannottix.recipe.commands.NoteCommand;
import com.giovannottix.recipe.domain.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: Giovanni Esposito.
 * @Date : 05/20/20, Wed
 */
class NotesCommandToNotesTest {
    private static final Long ID_VALUE = 1L;
    private static final String RECIPE_NOTES = "recipe";
    NotesCommandToNotes converter;

    @BeforeEach
    void setUp() {
        converter = new NotesCommandToNotes();
    }

    @Test
    void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new NoteCommand()));
    }

    @Test
    void convert() {
        //given
        NoteCommand notesCommand = new NoteCommand();
        notesCommand.setId(ID_VALUE);
        notesCommand.setRecipeNotes(RECIPE_NOTES);

        //when
        Note notes = converter.convert(notesCommand);

        //then
        assertEquals(ID_VALUE, notes.getId());
        assertEquals(RECIPE_NOTES, notes.getRecipeNotes());
    }
}