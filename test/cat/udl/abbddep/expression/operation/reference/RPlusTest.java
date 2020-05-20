package cat.udl.abbddep.expression.operation.reference;

import cat.udl.abbddep.cell.NotValidAddressException;
import cat.udl.abbddep.expression.operation.Operation;
import cat.udl.abbddep.expression.operation.Plus;
import cat.udl.abbddep.expression.value.SomeValue;
import org.junit.jupiter.api.BeforeEach;

import java.util.function.Function;

import static cat.udl.abbddep.spreadsheet.SpreadSheet.*;

public class RPlusTest extends AbstractReferenceOperationTest {

    @BeforeEach
    void setUp() throws NotValidAddressException {
        values1 = new int[]{3, 4};
        values2 = new int[]{6, 7};
        operationValues1 = (Operation) plus("a1", "a2");
        operationValues2 = (Operation) plus("b1", "b2");
        operationResult = (Operation) plus("a3", "b3");
        operationMixed = (Operation) plus("c1", "d1");
        operationIntInner = Integer::sum;
        operationIntOuter = Integer::sum;
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
}
