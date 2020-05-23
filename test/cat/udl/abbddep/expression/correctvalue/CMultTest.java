package cat.udl.abbddep.expression.correctvalue;

import cat.udl.abbddep.expression.operation.Mult;
import cat.udl.abbddep.expression.value.SomeValue;
import org.junit.jupiter.api.BeforeEach;

import java.util.function.Function;

class CMultTest extends AbstractCorrectValueOperationTest {

    @BeforeEach
    void setUp() {
        Function<int[], Mult> creator = (ar) -> new Mult(new SomeValue(ar[0]), new SomeValue(ar[1]));
        values1 = new int[]{3,4};
        values2 = new int[]{6,7};
        operationValues1 = creator.apply(values1);
        operationValues2 = creator.apply(values2);
        operationResult = new Mult(operationValues1, operationValues2);
        operationIntInner = (a, b) -> a * b;
        operationIntOuter = (a, b) -> a * b;
    }

}