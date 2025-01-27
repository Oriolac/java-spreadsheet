package cat.udl.abbddep.expression.novalue;

import cat.udl.abbddep.expression.operation.Operation;
import cat.udl.abbddep.expression.value.MaybeValue;
import cat.udl.abbddep.expression.value.NoValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class AbstractNoValueTest {

    Operation emptyOperation;
    Operation filledOperation;

    @BeforeEach
    void setUp(){
        setOperations();
    }

    protected abstract void setOperations();

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
