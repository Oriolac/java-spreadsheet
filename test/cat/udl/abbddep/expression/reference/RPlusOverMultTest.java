package cat.udl.abbddep.expression.reference;

import cat.udl.abbddep.sheet.NotValidAddressException;
import cat.udl.abbddep.expression.operation.Operation;

import static cat.udl.abbddep.spreadsheet.SpreadSheet.mult;
import static cat.udl.abbddep.spreadsheet.SpreadSheet.plus;

public class RPlusOverMultTest extends AbstractReferenceOperationTest {

    @Override
    void setUp() throws NotValidAddressException {
        values1 = new int[]{3, 4};
        values2 = new int[]{6, 7};
        valueOfMixed = 100;
        operationValues1 = (Operation) mult("a1", "a2");
        operationValues2 = (Operation) mult("b1", "b2");
        operationResult = (Operation) plus("a3", "b3");
        operationMixed = (Operation) mult("c1", "d1");
        operationIntInner = (a, b) -> a * b;
        operationIntOuter = Integer::sum;
        operationIntReference = (a, b) -> a * b;
    }

}
