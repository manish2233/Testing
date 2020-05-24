package com.giovannottix.recipe.converters;

import com.giovannottix.recipe.commands.UnitOfMeasureCommand;
import com.giovannottix.recipe.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * @author: Giovanni Esposito.
 * @Date : 05/20/20, Wed
 */
public class UnitOfMeasureCommandToUnitOfMeasureTest {

    static final Long ID = 1L;
    static final String UNIT_OF_MEASURE_DESC = "Unit of measure desc";

    UnitOfMeasureCommand unitOfMeasureCommand;

    UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;

    @BeforeEach
    void setUp() {
        unitOfMeasureCommandToUnitOfMeasure =
                new UnitOfMeasureCommandToUnitOfMeasure();

        unitOfMeasureCommand = UnitOfMeasureCommand.builder()
                .id(ID)
                .description(UNIT_OF_MEASURE_DESC).build();
    }

    @Test
    public void convertUnitOfMeasureCommandToUnitOfMeasureNullParam() throws Exception {
        assertNull(unitOfMeasureCommandToUnitOfMeasure.convert(null));
    }

    @Test
    public void convertUnitOfMeasureCommandToUnitOfMeasureEmptyParam() throws Exception {
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        UnitOfMeasure unitOfMeasure =
                unitOfMeasureCommandToUnitOfMeasure.convert(unitOfMeasureCommand);

        assertNotNull(unitOfMeasure);
        assertNull(unitOfMeasure.getId());
        assertNull(unitOfMeasure.getDescription());
    }

    @Test
    public void convertUnitOfMeasureCommandToUnitOfMeasureCommand() throws Exception {
        UnitOfMeasure unitOfMeasure =
                unitOfMeasureCommandToUnitOfMeasure.convert(unitOfMeasureCommand);

        assertNotNull(unitOfMeasure);
        assertEquals(ID, unitOfMeasure.getId());
        assertEquals(UNIT_OF_MEASURE_DESC, unitOfMeasure.getDescription());
    }
}
