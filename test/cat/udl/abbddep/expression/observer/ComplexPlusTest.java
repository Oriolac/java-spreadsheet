package cat.udl.abbddep.expression.observer;

import cat.udl.abbddep.expression.Expression;
import cat.udl.abbddep.sheet.NotValidAddressException;

import static cat.udl.abbddep.spreadsheet.SpreadSheet.plus;

public class ComplexPlusTest extends ComplexOperationTest {

    @Override
    protected String[] setValuesRef() {
        return new String[]{"a1", "a2", "a3", "a4"};
    }

    @Override
    protected String setComplexReference() {
        return "a2";
    }

    @Override
    protected int setComplexity() {
        return 4;
    }

    @Override
    protected Expression setComplexOperation() throws NotValidAddressException {
        return plus(plus(valuesRef[0], valuesRef[1]), plus(valuesRef[2], valuesRef[3]));
    }

    @Override
    protected int[] setValues() {
        return new int[]{2,4,6,1};
    }
}
