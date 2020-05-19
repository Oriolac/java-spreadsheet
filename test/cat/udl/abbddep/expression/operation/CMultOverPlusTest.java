package cat.udl.abbddep.expression.operation;

import cat.udl.abbddep.expression.value.SomeValue;
import org.junit.jupiter.api.BeforeEach;

import java.util.function.Function;

public class CMultOverPlusTest extends AbstractCorrectValueOperationTest {
    @BeforeEach
    void setUp() {
        Function<int[], Plus> creator = (ar) -> new Plus(new SomeValue(ar[0]), new SomeValue(ar[1]));
        values1 = new int[]{3, 4};
        values2 = new int[]{6, 7};
        operationValues1 = creator.apply(values1);
        operationValues2 = creator.apply(values2);
        operationResult = new Mult(operationValues1, operationValues2);
        operationIntInner = Integer::sum;
        operationIntOuter = (a, b) -> a * b;
    }
}