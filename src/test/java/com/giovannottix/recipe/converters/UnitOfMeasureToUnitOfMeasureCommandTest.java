package com.giovannottix.recipe.converters;

import com.giovannottix.recipe.commands.UnitOfMeasureCommand;
import com.giovannottix.recipe.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author: Giovanni Esposito.
 * @Date : 05/20/20, Wed
 */
public class UnitOfMeasureToUnitOfMeasureCommandTest {

    static final Long ID = 1L;
    static final String UNIT_OF_MEASURE_DESC = "Unit of measure desc";

    UnitOfMeasure unitOfMeasure;

    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    @BeforeEach
    void setUp() {
        unitOfMeasureToUnitOfMeasureCommand =
                new UnitOfMeasureToUnitOfMeasureCommand();

        unitOfMeasure = UnitOfMeasure.builder()
                .id(ID)
                .description(UNIT_OF_MEASURE_DESC).build();
    }

    @Test
    public void convertUnitOfMeasureToUnitOfMeasureCommandNullParam() throws Exception {
        assertNull(unitOfMeasureToUnitOfMeasureCommand.convert(null));
    }

    @Test
    public void convertUnitOfMeasureToUnitOfMeasureCommandEmptyParam() throws Exception {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        UnitOfMeasureCommand unitOfMeasureCommand =
                unitOfMeasureToUnitOfMeasureCommand.convert(unitOfMeasure);

        assertNotNull(unitOfMeasureCommand);
        assertNull(unitOfMeasureCommand.getId());
        assertNull(unitOfMeasureCommand.getDescription());
    }

    @Test
    public void convertUnitOfMeasureToUnitOfMeasureCommand() throws Exception {
        UnitOfMeasureCommand unitOfMeasureCommand =
                unitOfMeasureToUnitOfMeasureCommand.convert(unitOfMeasure);

        assertNotNull(unitOfMeasureCommand);
        assertEquals(ID, unitOfMeasureCommand.getId());
        assertEquals(UNIT_OF_MEASURE_DESC, unitOfMeasureCommand.getDescription());
    }
}
