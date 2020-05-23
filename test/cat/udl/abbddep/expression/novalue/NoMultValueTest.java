package cat.udl.abbddep.expression.novalue;

import cat.udl.abbddep.expression.operation.Mult;
import cat.udl.abbddep.expression.value.NoValue;
import org.junit.jupiter.api.BeforeEach;

public class NoMultValueTest extends AbstractNoValueTest {


    @Override
    protected void setOperations() {
        emptyOperation = new Mult(NoValue.INSTANCE, NoValue.INSTANCE);
        filledOperation = new Mult(emptyOperation, emptyOperation);
    }
}
