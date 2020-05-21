package cat.udl.abbddep.sheet;

import cat.udl.abbddep.cell.NotValidAddressException;
import cat.udl.abbddep.expression.Expression;
import cat.udl.abbddep.expression.value.NoValue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.function.BiFunction;
import java.util.function.Function;

import static cat.udl.abbddep.spreadsheet.SpreadSheet.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NotValidAddressExceptionTest {

    public static final String[] ADDRESS = new String[]{"k32", "aa43", "432as", "asfg", "4", "a", "543"};

    @ParameterizedTest
    @ValueSource(strings = {"k32", "aa43", "432as", "asfg", "4", "a", "543"})
    void throwsInPutTest(String address) throws NotValidAddressException {
        assertThrows(NotValidAddressException.class, () -> put(address, mult(1, 5)));
        assertThrows(NotValidAddressException.class, () -> put(address, 5));
        put("a1", 3);
        assertThrows(NotValidAddressException.class, () -> put(address, "a1"));
        assertThrows(NotValidAddressException.class, () -> put("a1", address));
    }

    @ParameterizedTest
    @ValueSource(strings = {"k32", "aa43", "432as", "asfg", "4", "a", "543"})
    void throwsInGetTest(String address) {
        assertThrows(NotValidAddressException.class, () -> get(address));
    }


    @ParameterizedTest
    @ValueSource(strings = {"k32", "aa43", "432as", "asfg", "4", "a", "543"})
    void throwsInPlusTest(String address) {
        assertThrows(NotValidAddressException.class, () -> plus(NoValue.INSTANCE, address));
        assertThrows(NotValidAddressException.class, () -> plus(address, "a1"));
        assertThrows(NotValidAddressException.class, () -> plus("a1", address));
        assertThrows(NotValidAddressException.class, () -> plus(address, address));
        assertThrows(NotValidAddressException.class, () -> plus(address, 1));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a10", "z1", "k32", "aa43", "432as", "asfg", "4", "a", "543"})
    void throwsInMultTest(String address) {
        assertThrows(NotValidAddressException.class, () -> mult(NoValue.INSTANCE, address));
        assertThrows(NotValidAddressException.class, () -> mult(address, "a1"));
        assertThrows(NotValidAddressException.class, () -> mult("a1", address));
        assertThrows(NotValidAddressException.class, () -> mult(address, address));
        assertThrows(NotValidAddressException.class, () -> mult(address, 1));
    }

    @AfterEach
    void close() {
        clear();
    }
}