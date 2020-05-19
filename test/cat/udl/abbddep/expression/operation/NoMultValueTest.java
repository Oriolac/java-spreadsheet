package cat.udl.abbddep.expression.operation;

import cat.udl.abbddep.expression.value.NoValue;
import org.junit.jupiter.api.BeforeEach;

public class NoMultValueTest extends AbstractNoValueTest {

    @BeforeEach
    void setUp() {
        emptyOperation = new Mult(NoValue.INSTANCE, NoValue.INSTANCE);
        filledOperation = new Mult(emptyOperation, emptyOperation);
    }
}
