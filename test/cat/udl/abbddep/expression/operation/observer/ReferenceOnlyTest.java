package cat.udl.abbddep.expression.operation.observer;

import cat.udl.abbddep.cell.NotValidAddressException;
import cat.udl.abbddep.expression.value.MaybeValue;
import cat.udl.abbddep.expression.value.SomeValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static cat.udl.abbddep.spreadsheet.SpreadSheet.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReferenceOnlyTest {

    private int[] values;
    private String[] letters;

    @BeforeEach
    void setUp() throws NotValidAddressException {
        values = new int[]{3, 5, 7};
        letters = new String[]{"a", "b", "c", "d"};
        put("a1", values[0]);
        put("a2", values[1]);
        put("a3", values[2]);
        put("b1", "a1");
        put("b2", "a2");
        put("b3", "a3");
        put("c1", "b1");
        put("c2", "b2");
        put("c3", "b3");
        put("d1", "c1");
        put("d2", "c2");
        put("d3", "c3");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void testValues(int num) throws NotValidAddressException {
        for (String letter : letters) {
            MaybeValue mValue = get(letter + num);
            if (mValue.hasValue())
                assertEquals(values[num - 1], ((SomeValue) mValue).getValue());
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void testFirstRefValue(int num) throws NotValidAddressException {
        values[num - 1] = 100;
        put("a" + num, values[num - 1]);
        for (String letter : letters) {
            MaybeValue mValue = get(letter + num);
            if (mValue.hasValue())
                assertEquals(values[num - 1], ((SomeValue) mValue).getValue(), "Failed in Cell: " + letter + num);
        }
    }

}
