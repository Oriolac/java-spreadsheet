package cat.udl.abbddep.spreadsheet;

import cat.udl.abbddep.cell.NotValidAddressException;
import cat.udl.abbddep.expression.Expression;
import cat.udl.abbddep.expression.operation.Plus;
import cat.udl.abbddep.expression.value.MaybeValue;
import cat.udl.abbddep.expression.value.SomeValue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static cat.udl.abbddep.spreadsheet.SpreadSheet.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class SpreadSheetTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void testPlus() throws NotValidAddressException {
        MaybeValue value = plus(3, 5).evaluate();
        assertTrue(value.hasValue());
        assertTrue(value instanceof SomeValue);
        assertEquals(8, ((SomeValue) value).getValue());
        put("a1", value);
        assertEquals(value, get("a1"));
        Expression expr = plus("a1", 2);
        put("a2", expr);
        assertTrue(get("a2").hasValue());
        assertTrue(get("a2") instanceof SomeValue);
        assertEquals(get("a2").hashCode(), get("a2").hashCode());
        assertEquals(10, ((SomeValue) get("a2")).getValue());
    }


    @AfterEach
    void tearDown() {
    }
}