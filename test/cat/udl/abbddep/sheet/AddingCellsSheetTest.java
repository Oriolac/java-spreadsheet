package cat.udl.abbddep.sheet;

import cat.udl.abbddep.cell.NotValidAddressException;
import cat.udl.abbddep.expression.value.MaybeValue;
import cat.udl.abbddep.expression.value.SomeValue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import static cat.udl.abbddep.spreadsheet.SpreadSheet.*;
import static org.junit.jupiter.api.Assertions.*;

public class AddingCellsSheetTest {

    int firstValue = 3;
    private final int SHEETDIM = 5;

    @BeforeEach
    void setUp() throws NotValidAddressException {
        put("a1", firstValue);
    }

    @Test
    void creatingAllCells() throws NotValidAddressException {
        Character[] chars = new Character[]{'a', 'b', 'c', 'd', 'e'};
        for (int i = 1; i <= SHEETDIM; i++) {
            for (Character ch : chars) {
                put(String.valueOf(ch) + i, ch * i);
            }
        }
        for (int i = 1; i <= SHEETDIM; i++) {
            for (Character ch : chars) {
                MaybeValue value = get(String.valueOf(ch) + i);
                assertTrue(value.hasValue() && value instanceof SomeValue);
                assertEquals(ch * i, ((SomeValue) value).getValue());
            }
        }
    }

    @Test
    void createValueCell() throws NotValidAddressException {
        MaybeValue value = get("a1");
        assertTrue(value.hasValue());
        assertTrue(value instanceof SomeValue);
        assertEquals(firstValue, ((SomeValue) value).getValue());
    }

    @Test
    void createReferenceCell() throws NotValidAddressException {
        put("a2", "a1");
        MaybeValue value = get("a2");
        assertTrue(value.hasValue());
        assertTrue(value instanceof SomeValue);
        assertEquals(firstValue, ((SomeValue) value).getValue());
    }

    @Test
    void createReferencesCell() throws NotValidAddressException {
        put("a2", "a1");
        put("a3", "a2");
        MaybeValue value = get("a3");
        assertTrue(value.hasValue());
        assertTrue(value instanceof SomeValue);
        assertEquals(firstValue, ((SomeValue) value).getValue());
    }

    @Test
    void createOperationCell() throws NotValidAddressException {
        put("a2", plus(firstValue, 0));
        MaybeValue value = get("a2");
        assertTrue(value.hasValue());
        assertTrue(value instanceof SomeValue);
        assertEquals(firstValue, ((SomeValue) value).getValue());
    }

    @Test
    void createOperationCellWithReference() throws NotValidAddressException {
        put("a2", firstValue);
        put("a3", plus("a2", firstValue));
        MaybeValue value = get("a3");
        assertTrue(value.hasValue());
        assertTrue(value instanceof SomeValue);
        assertEquals(firstValue * 2, ((SomeValue) value).getValue());
    }

    @Test
    void createOperationCellWithReferences() throws NotValidAddressException {
        put("a3", plus("a2", "a1"));
        put("a2", firstValue);
        MaybeValue value = get("a3");
        assertTrue(value.hasValue());
        assertTrue(value instanceof SomeValue);
        assertEquals(firstValue * 2, ((SomeValue) value).getValue());
    }

    @Test
    void createReferenceAfterValueTest() throws NotValidAddressException {
        put("a3", "a2");
        MaybeValue expNoValue = get("a3");
        assertFalse(expNoValue.hasValue());
        put("a2", "a1");
        MaybeValue value = get("a2");
        assertTrue(value.hasValue());
        assertTrue(value instanceof SomeValue);
        assertEquals(firstValue, ((SomeValue) value).getValue());

    }

    @Test
    void operationsInSheet() throws NotValidAddressException {
        int[] values = new int[]{3,4,2,4};
        String ref = "a1";
        plusOverMult(values, ref);
        ref ="a2";
        multOverPlus(values, ref);
        values = new int[]{-3,-4,-2,-4};
        ref = "b1";
        plusOverMult(values, ref);
        ref ="b2";
        multOverPlus(values, ref);
    }

    private void multOverPlus(int[] values, String ref) throws NotValidAddressException {
        put(ref, mult(plus(values[0], values[1]), plus(values[2], values[3])));
        assertTrue(get(ref).hasValue() && get(ref) instanceof SomeValue);
        assertEquals((values[0] + values[1]) * (values[2] + values[3]), ((SomeValue) get(ref)).getValue());
    }

    private void plusOverMult(int[] values, String ref) throws NotValidAddressException {
        put(ref, plus(mult(values[0], values[1]), mult(values[2], values[3])));
        assertTrue(get(ref).hasValue() && get(ref) instanceof SomeValue);
        assertEquals(values[0] * values[1] + values[2] * values[3], ((SomeValue) get(ref)).getValue());
    }


    @AfterEach
    void close() {
        clear();
    }
}
