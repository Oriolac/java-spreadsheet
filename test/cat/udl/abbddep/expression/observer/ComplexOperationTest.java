package cat.udl.abbddep.expression.observer;

import cat.udl.abbddep.cell.Cell;
import cat.udl.abbddep.expression.Expression;
import cat.udl.abbddep.expression.operation.Operation;
import cat.udl.abbddep.sheet.NotValidAddressException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static cat.udl.abbddep.spreadsheet.SpreadSheet.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class ComplexOperationTest {

    protected int LENGTH;
    protected String[] valuesRef;
    protected int[] values;
    private Operation operation;
    private String complexRef;

    @BeforeEach
    void setUp() throws NotValidAddressException {
        LENGTH = setComplexity();
        values = setValues();
        valuesRef = setValuesRef();
        for (int i = 0; i < LENGTH; i++)
            put(valuesRef[i], values[i]);
        operation = (Operation) setComplexOperation();
        complexRef = setComplexReference();
        put(complexRef, operation);
    }

    protected abstract int setComplexity();

    protected abstract int[] setValues();

    protected abstract String[] setValuesRef();

    protected abstract Expression setComplexOperation() throws NotValidAddressException;

    protected abstract String setComplexReference();


    @Test
    void getSizeObservables() throws NotValidAddressException {
        List<Cell> cells = operation.getCellsObservables();
        assertEquals(LENGTH, cells.size());
        assertEquals(0, get(complexRef).getCellsObservables().size());
    }

    @AfterEach
    void tearDown() {
        clear();
    }

}
