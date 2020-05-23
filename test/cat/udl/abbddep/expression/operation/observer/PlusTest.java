package cat.udl.abbddep.expression.operation.observer;

import cat.udl.abbddep.cell.NotValidAddressException;
import cat.udl.abbddep.expression.Expression;

import java.util.List;

import static cat.udl.abbddep.spreadsheet.SpreadSheet.*;
import static java.util.List.*;

public class PlusTest extends ObserversTest{
    @Override
    protected void putOperations() throws NotValidAddressException {
        put("b1", operation1);
        put("b2", operation2);
        put("b3", operation3);
        opRefs = List.of("b1", "b2", "b3");
    }

    @Override
    protected void setOperations() throws NotValidAddressException {
        opInt1 = Integer::sum;
        opInt2 = (a, b) -> a * b;
        opInt3 = Integer::sum;
        operation1 = plus("a1", "a2");
        operation2 = mult("b1", interValue);
        operation3 = plus("a3", "b2");
    }

    @Override
    protected void putValuesRefs() throws NotValidAddressException {
        put("a1", firstValueRef);
        put("a2", secValueRef);
        put("a3", thirdValueRef);
        valuesRefs = List.of("a1", "a2", "a3");
    }

    @Override
    protected void setValues() {
        firstValueRef = 3;
        secValueRef = 4;
        interValue = 2;
        thirdValueRef = 10;
    }
}
