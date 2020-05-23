package cat.udl.abbddep.expression.operation.observer;

import cat.udl.abbddep.cell.Cell;
import cat.udl.abbddep.cell.NotValidAddressException;
import cat.udl.abbddep.expression.Expression;
import cat.udl.abbddep.expression.operation.Operation;
import cat.udl.abbddep.expression.operation.OperationInt;
import cat.udl.abbddep.expression.value.SomeValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static cat.udl.abbddep.spreadsheet.SpreadSheet.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class ObserversTest {

    int firstValueRef;
    int secValueRef;
    int interValue;
    int thirdValueRef;
    Expression operation1;
    Expression operation2;
    Expression operation3;
    OperationInt opInt1;
    OperationInt opInt2;
    OperationInt opInt3;
    List<String> valuesRefs;
    List<String> opRefs;

    @BeforeEach
    void abstractSetUp() throws NotValidAddressException {
        setValues();
        putValuesRefs();
        setOperations();
        putOperations();
        put(valuesRefs.get(0), firstValueRef);
        put(valuesRefs.get(1), secValueRef);
        put(valuesRefs.get(2), thirdValueRef);
        put(opRefs.get(0), operation1);
        put(opRefs.get(1), operation2);
        put(opRefs.get(2), operation3);
    }

    protected abstract void putOperations() throws NotValidAddressException;

    protected abstract void setOperations() throws NotValidAddressException;

    protected abstract void putValuesRefs() throws NotValidAddressException;

    protected abstract void setValues();

    @Test
    void checkOperation1Cells() throws NotValidAddressException {
        List<Cell> cells = new LinkedList<>();
        operation1.getCellsDependency(cells);
        assertEquals(2, cells.size());
        assertValue(cells, 0, firstValueRef);
        assertValue(cells, 1, secValueRef);
        assertTrue(operation1.evaluate().hasValue() && operation1.evaluate() instanceof SomeValue);
        assertEquals(opInt1.operate(firstValueRef, secValueRef), ((SomeValue) operation1.evaluate()).getValue());
    }

    @Test
    void checkOperation2Cells() throws NotValidAddressException {
        List<Cell> cells = new LinkedList<>();
        operation2.getCellsDependency(cells);
        assertEquals(1, cells.size());
        assertValue(cells, 0, firstValueRef);
        assertValue(cells, 1, secValueRef);
        assertTrue(operation2.evaluate().hasValue() && operation2.evaluate() instanceof SomeValue);
        assertEquals(opInt2.operate(interValue, opInt1.operate(firstValueRef, secValueRef)),
                ((SomeValue) operation2.evaluate()).getValue());
    }

    @Test
    void checkOperation3Cells() throws NotValidAddressException {
        List<Cell> cells = new LinkedList<>();
        operation3.getCellsDependency(cells);
        assertEquals(3, cells.size());
        assertValue(cells, 0, firstValueRef);
        assertValue(cells, 1, secValueRef);
        assertValue(cells, 2, thirdValueRef);
        assertTrue(operation3.evaluate().hasValue() && operation3.evaluate() instanceof SomeValue);
        assertEquals(opInt3.operate(
                opInt2.operate(
                        interValue, opInt1
                                .operate(firstValueRef, secValueRef)), ((SomeValue) operation2.evaluate()).getValue()),
                thirdValueRef);
    }

    void assertValue(List<Cell> cells, int i, int value) throws NotValidAddressException {
        assertEquals(get(valuesRefs.get(i)), cells.get(i).evaluate());
        assertTrue(get(valuesRefs.get(i)).hasValue() && get(valuesRefs.get(i)) instanceof SomeValue);
        assertEquals(value, ((SomeValue) get(valuesRefs.get(i))).getValue());
    }
}
