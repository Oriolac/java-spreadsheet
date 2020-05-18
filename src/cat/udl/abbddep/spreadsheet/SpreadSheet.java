package cat.udl.abbddep.spreadsheet;


import cat.udl.abbddep.cell.Cell;
import cat.udl.abbddep.cell.NotValidAddressException;
import cat.udl.abbddep.expression.Expression;
import cat.udl.abbddep.expression.operation.Plus;
import cat.udl.abbddep.expression.reference.Reference;
import cat.udl.abbddep.expression.value.MaybeValue;
import cat.udl.abbddep.expression.value.SomeValue;
import cat.udl.abbddep.sheet.SheetTable;

public class SpreadSheet {

    private static int SIZE = 5;
    private static final SheetTable SHEET = new SheetTable(SIZE);
    /*
    Abstract Factory to get all the operations¿?
     */

    public static Expression plus(Expression expr1, Expression expr2) {
        return new Plus(expr1, expr2);
    }

    public static Expression plus(Expression expr1, int value2) {
        return new Plus(expr1, new SomeValue(value2));
    }

    public static Expression plus(Expression expr1, String ref2) throws NotValidAddressException {
        Cell cell = SHEET.getCell(ref2);
        return new Plus(expr1, new Reference(cell));
    }

    public static Expression plus(int value1, Expression expr2) {
        return new Plus(new SomeValue(value1), expr2);
    }

    public static Expression plus(String ref1, Expression expr2) throws NotValidAddressException {
        Cell cell = SHEET.getCell(ref1);
        return new Plus(new Reference(cell), expr2);
    }

    public static Expression plus(String ref1, String ref2) throws NotValidAddressException {
        Cell cell1 = SHEET.getCell(ref1);
        Cell cell2 = SHEET.getCell(ref2);
        return new Plus(new Reference(cell1), new Reference(cell2));
    }

    public static Expression plus(int value1, int value2) {
        return new Plus(new SomeValue(value1), new SomeValue(value2));
    }

    public static MaybeValue get(String address) {
        throw new Error("SORRY");
    }

    public static void put(String name, Expression expr) {
        throw new Error("SORRY");
    }

    public static void put(String name, int value) {
        throw new Error("SORRY");
    }

    public static void put(String name, String refName) {
        throw new Error("SORRY");
    }

    public static void clear() {
        throw new Error("SORRY");
    }


}
