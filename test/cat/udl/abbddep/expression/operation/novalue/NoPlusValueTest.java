package cat.udl.abbddep.expression.operation.novalue;

import cat.udl.abbddep.expression.operation.Plus;
import cat.udl.abbddep.expression.value.NoValue;
import org.junit.jupiter.api.BeforeEach;

public class NoPlusValueTest extends AbstractNoValueTest {

    @BeforeEach
    void setUp() {
        emptyOperation = new Plus(NoValue.INSTANCE, NoValue.INSTANCE);
        filledOperation = new Plus(emptyOperation, emptyOperation);
    }
}
