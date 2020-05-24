package cat.udl.abbddep.spreadsheet;


import cat.udl.abbddep.cell.Cell;
import cat.udl.abbddep.expression.Expression;
import cat.udl.abbddep.expression.operation.Mult;
import cat.udl.abbddep.expression.operation.Plus;
import cat.udl.abbddep.expression.reference.Reference;
import cat.udl.abbddep.expression.value.MaybeValue;
import cat.udl.abbddep.expression.value.SomeValue;
import cat.udl.abbddep.sheet.NotValidAddressException;
import cat.udl.abbddep.sheet.Sheet;
import cat.udl.abbddep.sheet.SheetTable;

public class SpreadSheet {

    private static final int SIZE = 5;
    private static final Sheet SHEET = new SheetTable(SIZE);

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

    public static Expression plus(String ref1, String ref2) throws NotValidAddressException {
        Cell cell1 = SHEET.getCell(ref1);
        Cell cell2 = SHEET.getCell(ref2);
        return new Plus(new Reference(cell1), new Reference(cell2));
    }

    public static Expression plus(String ref1, int value2) throws NotValidAddressException {
        Cell cell1 = SHEET.getCell(ref1);
        return new Plus(new Reference(cell1), new SomeValue(value2));
    }

    public static Expression plus(int value1, int value2) {
        return new Plus(new SomeValue(value1), new SomeValue(value2));
    }

    public static Expression mult(Expression expr1, Expression expr2) {
        return new Mult(expr1, expr2);
    }

    public static Expression mult(Expression expr1, int value2) {
        return new Mult(expr1, new SomeValue(value2));
    }

    public static Expression mult(Expression expr1, String ref2) throws NotValidAddressException {
        Cell cell = SHEET.getCell(ref2);
        return new Mult(expr1, new Reference(cell));
    }

    public static Expression mult(String ref1, String ref2) throws NotValidAddressException {
        Cell cell1 = SHEET.getCell(ref1);
        Cell cell2 = SHEET.getCell(ref2);
        return new Mult(new Reference(cell1), new Reference(cell2));
    }

    public static Expression mult(String ref1, int value2) throws NotValidAddressException {
        Cell cell1 = SHEET.getCell(ref1);
        return new Mult(new Reference(cell1), new SomeValue(value2));
    }

    public static Expression mult(int value1, int value2) {
        return new Mult(new SomeValue(value1), new SomeValue(value2));
    }

    public static MaybeValue get(String address) throws NotValidAddressException {
        return SHEET.getCell(address).evaluate();
    }

    public static void put(String name, Expression expr) throws NotValidAddressException {
        SHEET.putCell(name, expr);
    }

    public static void put(String name, int value) throws NotValidAddressException {
        put(name, new SomeValue(value));
    }

    public static void put(String name, String refName) throws NotValidAddressException {
        put(name, new Reference(SHEET.getCell(refName)));
    }

    public static void clear() {
        SHEET.clear();
    }


}
