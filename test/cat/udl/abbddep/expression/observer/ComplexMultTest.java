package cat.udl.abbddep.expression.observer;

import cat.udl.abbddep.expression.Expression;
import cat.udl.abbddep.sheet.NotValidAddressException;

import static cat.udl.abbddep.spreadsheet.SpreadSheet.mult;

public class ComplexMultTest extends ComplexOperationTest {


    @Override
    protected int setComplexity() {
        return 6;
    }

    @Override
    protected int[] setValues() {
        return new int[]{6, 2, 7, 2, 8, 1};
    }

    @Override
    protected String[] setValuesRef() {
        return new String[]{"a1", "a2", "a3", "a4", "a5", "b1"};
    }

    @Override
    protected Expression setComplexOperation() throws NotValidAddressException {
        return mult(mult(valuesRef[0], valuesRef[1]), mult(mult(valuesRef[2], valuesRef[3]), mult(valuesRef[4], valuesRef[5])));
    }

    @Override
    protected String setComplexReference() {
        return "b2";
    }
}
