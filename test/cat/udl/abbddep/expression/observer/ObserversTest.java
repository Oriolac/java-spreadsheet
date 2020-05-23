package cat.udl.abbddep.expression.observer;

import cat.udl.abbddep.cell.Cell;
import cat.udl.abbddep.expression.Expression;
import cat.udl.abbddep.expression.operation.OperationInt;
import cat.udl.abbddep.expression.value.MaybeValue;
import cat.udl.abbddep.expression.value.SomeValue;
import cat.udl.abbddep.sheet.NotValidAddressException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static cat.udl.abbddep.spreadsheet.SpreadSheet.*;
import static org.junit.jupiter.api.Assertions.*;

public abstract class ObserversTest {

    int firstValueRef;
    int newValueRef;
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
        List<Cell> cells = operation1.getCellsObservables();
        assertEquals(2, cells.size());
        assertValue(cells, 0, firstValueRef);
        assertValue(cells, 1, secValueRef);
        assertTrue(operation1.evaluate().hasValue() && operation1.evaluate() instanceof SomeValue);
        assertEquals(opInt1.operate(firstValueRef, secValueRef), ((SomeValue) get(opRefs.get(0))).getValue());
    }

    @Test
    void checkOperation2Cells() throws NotValidAddressException {
        List<Cell> cells = operation2.getCellsObservables();
        assertEquals(1, cells.size());
        assertValue(cells, 0, opInt1.operate(firstValueRef, secValueRef));
        assertTrue(operation2.evaluate().hasValue() && operation2.evaluate() instanceof SomeValue);
        assertEquals(opInt2.operate(interValue, opInt1.operate(firstValueRef, secValueRef)),
                ((SomeValue) get(opRefs.get(1))).getValue());
    }

    @Test
    void checkOperation3Cells() throws NotValidAddressException {
        List<Cell> cells = operation3.getCellsObservables();
        assertEquals(2, cells.size());
        assertValue(cells, 0, opInt2.operate(opInt1.operate(firstValueRef, secValueRef), interValue));
        assertValue(cells, 1, thirdValueRef);
        assertTrue(operation3.evaluate().hasValue() && operation3.evaluate() instanceof SomeValue);
        assertEquals(opInt3.operate(
                opInt2.operate(
                        interValue, opInt1
                                .operate(firstValueRef, secValueRef)), thirdValueRef),
                ((SomeValue) get(opRefs.get(2))).getValue());
    }

    @Test
    void changingFirstValueTest() throws NotValidAddressException {
        MaybeValue previousValue = get(opRefs.get(2));
        put(valuesRefs.get(0), newValueRef);
        assertEquals(opInt3.operate(
                opInt2.operate(
                        interValue, opInt1
                                .operate(newValueRef, secValueRef)), thirdValueRef),
                ((SomeValue) get(opRefs.get(2))).getValue());
        assertNotEquals(previousValue.hashCode(), get(opRefs.get(2)));
    }

    @Test
    void changingThirdValueTest() throws NotValidAddressException {
        MaybeValue previousValue = get(opRefs.get(2));
        put(valuesRefs.get(2), newValueRef);
        assertEquals(opInt3.operate(
                opInt2.operate(
                        interValue, opInt1
                                .operate(firstValueRef, secValueRef)), newValueRef),
                ((SomeValue) get(opRefs.get(2))).getValue());
        assertNotEquals(previousValue.hashCode(), get(opRefs.get(2)));
    }

    @Test
    void removingDependenciesInOperation1() throws NotValidAddressException {
        put(opRefs.get(0), 10);
        List<Cell> cells = operation2.getCellsObservables();
        assertEquals(1, cells.size());
        cells = operation3.getCellsObservables();
        assertEquals(2, cells.size());
        assertTrue(get(opRefs.get(2)).hasValue() && get(opRefs.get(2)) instanceof SomeValue);
        assertEquals(opInt3.operate(opInt2.operate(10, interValue), thirdValueRef), ((SomeValue) get(opRefs.get(2))).getValue());
    }

    @Test
    void removingDependenciesInOperation3() throws NotValidAddressException {
        put(opRefs.get(0), 10);

    }

    void assertValue(List<Cell> cells, int i, int value) throws NotValidAddressException {
        assertTrue(get(valuesRefs.get(i)).hasValue() && get(valuesRefs.get(i)) instanceof SomeValue);
        assertEquals(value, ((SomeValue) cells.get(i).evaluate()).getValue(), "Expected value was of the operation was " + value);
    }

    @AfterEach
    void tearDown() {
        clear();
    }
}
