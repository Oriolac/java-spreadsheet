package cat.udl.abbddep.expression.reference;

import cat.udl.abbddep.sheet.NotValidAddressException;
import cat.udl.abbddep.expression.operation.Operation;

import static cat.udl.abbddep.spreadsheet.SpreadSheet.plus;

public class RMultTest extends AbstractReferenceOperationTest {
    @Override
    void setUp() throws NotValidAddressException {
        values1 = new int[]{3, 4};
        values2 = new int[]{6, 7};
        valueOfMixed = 100;
        operationValues1 = (Operation) plus("a1", "a2");
        operationValues2 = (Operation) plus("b1", "b2");
        operationResult = (Operation) plus("a3", "b3");
        operationMixed = (Operation) plus("c1", "d1");
        operationIntInner = Integer::sum;
        operationIntReference = Integer::sum;
        operationIntOuter = Integer::sum;
    }
}
