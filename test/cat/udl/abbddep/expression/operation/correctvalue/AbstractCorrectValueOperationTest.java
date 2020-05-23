package cat.udl.abbddep.expression.operation.correctvalue;

import cat.udl.abbddep.cell.NotValidAddressException;
import cat.udl.abbddep.expression.operation.Operation;
import cat.udl.abbddep.expression.operation.OperationInt;
import cat.udl.abbddep.expression.value.MaybeValue;
import cat.udl.abbddep.expression.value.SomeValue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.function.BinaryOperator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static cat.udl.abbddep.spreadsheet.SpreadSheet.*;

public abstract class AbstractCorrectValueOperationTest {

    int[] values1;
    int[] values2;
    Operation operationValues1;
    Operation operationValues2;
    Operation operationResult;
    OperationInt operationIntInner;
    BinaryOperator<Integer> opInner = (f, s) -> operationIntInner.operate(f, s);
    OperationInt operationIntOuter;
    BinaryOperator<Integer> opOuter = (f, s) -> operationIntOuter.operate(f, s);


    @Test
    void instancesOfSomeValueTest() {
        MaybeValue mValue1 = operationValues1.evaluate();
        assertTrue(mValue1 instanceof SomeValue);
        MaybeValue mValue2 = operationValues2.evaluate();
        assertTrue(mValue2 instanceof SomeValue);
        MaybeValue mValueRes = operationResult.evaluate();
        assertTrue(mValueRes instanceof SomeValue);
    }

    @Test
    void getValueOperationTest() {
        MaybeValue mValue1 = operationValues1.evaluate();
        assertTrue(mValue1 instanceof SomeValue);
        assertEquals(opInner.apply(values1[0], values1[1]), ((SomeValue) mValue1).getValue());
        MaybeValue mValue2 = operationValues2.evaluate();
        assertTrue(mValue2 instanceof SomeValue);
        assertEquals(opInner.apply(values2[0], values2[1]), ((SomeValue) mValue2).getValue());
    }

    @Test
    void getResultTest() {
        MaybeValue mValueRes = operationResult.evaluate();
        assertTrue(mValueRes instanceof SomeValue);
        int res = opOuter.apply(opInner.apply(values1[0], values1[1]), opInner.apply(values2[0], values2[1]));
        assertEquals(res, ((SomeValue) mValueRes).getValue());
    }

    @AfterEach
    void tearDown() {
        clear();
    }
}
