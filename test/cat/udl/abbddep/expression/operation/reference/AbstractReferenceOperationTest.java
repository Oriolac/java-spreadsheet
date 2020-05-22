package cat.udl.abbddep.expression.operation.reference;

import cat.udl.abbddep.cell.NotValidAddressException;
import cat.udl.abbddep.expression.operation.Operation;
import cat.udl.abbddep.expression.operation.OperationInt;
import cat.udl.abbddep.expression.value.MaybeValue;
import cat.udl.abbddep.expression.value.SomeValue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.function.BinaryOperator;

import static cat.udl.abbddep.spreadsheet.SpreadSheet.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class AbstractReferenceOperationTest {


    int[] values1;
    int[] values2;
    int valueOfMixed;
    Operation operationValues1;
    Operation operationValues2;
    Operation operationResult;
    Operation operationMixed;
    OperationInt operationIntInner;
    BinaryOperator<Integer> opInner = (f, s) -> operationIntInner.operate(f, s);
    OperationInt operationIntOuter;
    OperationInt operationIntReference;
    BinaryOperator<Integer> opRefer = (f, s) -> operationIntReference.operate(f, s);
    BinaryOperator<Integer> opOuter = (f, s) -> operationIntOuter.operate(f, s);

    abstract void setUp() throws NotValidAddressException;

    @BeforeEach
    void abstractSetUp() throws NotValidAddressException {
        setUp();
        put("c1", operationResult);
        put("a3", operationValues1);
        put("a1", values1[0]);
        put("a2", values1[1]);
        put("b3", operationValues2);
        put("b1", values2[0]);
        put("b2", values2[1]);
        put("d1", valueOfMixed);
        put("d2", operationMixed);
    }


    @ParameterizedTest
    @ValueSource(strings = {"a1", "a2", "a3", "b1", "b2", "b3", "c1", "d1", "d2"})
    void instancesOfSomeValueTest(String address) throws NotValidAddressException {
        MaybeValue mValueRes = get(address);
        assertTrue(mValueRes.hasValue());
        assertTrue(mValueRes instanceof SomeValue);
    }

    @Test
    void operationsWithSimpleReferencesTest() throws NotValidAddressException {
        assertTrue(get("a3").hasValue());
        assertTrue(get("a3") instanceof SomeValue);
        assertEquals(opInner.apply(values1[0], values1[1]), ((SomeValue) get("a3")).getValue());
        assertEquals(get("a3").hashCode(), get("a3").hashCode());
        assertTrue(get("b3").hasValue());
        assertTrue(get("b3") instanceof SomeValue);
        assertEquals(opInner.apply(values2[0], values2[1]), ((SomeValue) get("b3")).getValue());
        assertEquals(get("b3").hashCode(), get("b3").hashCode());
    }

    @Test
    void getResultTest() throws NotValidAddressException {
        MaybeValue mValueRes = get("c1");
        assertTrue(mValueRes instanceof SomeValue);
        int res = opOuter.apply(opInner.apply(values1[0], values1[1]), opInner.apply(values2[0], values2[1]));
        assertEquals(res, ((SomeValue) mValueRes).getValue());
    }

    @Test
    void getMixedTest() throws NotValidAddressException {
        MaybeValue mValueRes = get("d2");
        assertTrue(mValueRes instanceof SomeValue);
        int res = opRefer
                .apply(opOuter.apply(
                        opInner.apply(values1[0], values1[1]), opInner.apply(values2[0], values2[1])),
                        valueOfMixed);
        assertEquals(res, ((SomeValue) mValueRes).getValue());
    }

    @AfterEach
    void close() {
        clear();
    }

}
