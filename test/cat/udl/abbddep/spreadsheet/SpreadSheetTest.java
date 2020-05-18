package cat.udl.abbddep.spreadsheet;

import cat.udl.abbddep.expression.value.MaybeValue;
import cat.udl.abbddep.expression.value.SomeValue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static cat.udl.abbddep.spreadsheet.SpreadSheet.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


class SpreadSheetTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void testPlus() {
        MaybeValue value = plus(3, 5).evaluate();
        if (value.hasValue()) {
            SomeValue svalue  =(SomeValue) value;
            assertEquals(8, svalue.getValue());
        }
    }


    @AfterEach
    void tearDown() {
    }
}