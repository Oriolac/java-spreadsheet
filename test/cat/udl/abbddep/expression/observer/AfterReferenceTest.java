package cat.udl.abbddep.expression.observer;

import cat.udl.abbddep.expression.operation.Operation;
import cat.udl.abbddep.expression.visitor.ObservablesVisitor;
import cat.udl.abbddep.sheet.NotValidAddressException;
import cat.udl.abbddep.expression.value.MaybeValue;
import cat.udl.abbddep.expression.value.SomeValue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static cat.udl.abbddep.spreadsheet.SpreadSheet.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AfterReferenceTest {

    @Test
    void onlyReferences() throws NotValidAddressException {
        put("a2", "a1");
        put("a1", 5);
        MaybeValue mValue = get("a2");
        assertTrue(mValue.hasValue() && mValue instanceof SomeValue);
        assertEquals(5, ((SomeValue) mValue).getValue());
    }


    @Test
    void withValuesOperations() throws NotValidAddressException {
        put("a2", "a1");
        put("a1", plus(4, 1));
        MaybeValue mValue = get("a2");
        assertTrue(mValue.hasValue() && mValue instanceof SomeValue);
        assertEquals(5, ((SomeValue) mValue).getValue());
    }

    @Test
    void withReferenceOperations() throws NotValidAddressException {
        put("a2", "a1");
        put("a1", plus("b1", "b2"));
        ObservablesVisitor visitor = new ObservablesVisitor();
        visitor.visit((Operation) plus("b1", "b2"));
        assertEquals(2, visitor.getObservables().size());
        put("b1", 4);
        put("b2", 1);
        MaybeValue mValue = get("a2");
        assertTrue(mValue.hasValue() && mValue instanceof SomeValue);
        assertEquals(5, ((SomeValue) mValue).getValue());

    }



    @AfterEach
    void tearDown() {
        clear();
    }
}
