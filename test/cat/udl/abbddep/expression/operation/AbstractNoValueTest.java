package cat.udl.abbddep.expression.operation;

import cat.udl.abbddep.expression.value.MaybeValue;
import cat.udl.abbddep.expression.value.NoValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class AbstractNoValueTest {

    Operation emptyOperation;
    Operation filledOperation;

    @Test
    void noValueInSimpleOperationTest() {
        MaybeValue value = emptyOperation.evaluate();
        assertTrue(value instanceof NoValue);
    }

    @Test
    void noValueInComposedOperationTest() {
        MaybeValue value = filledOperation.evaluate();
        assertTrue(value instanceof NoValue);
    }

}
