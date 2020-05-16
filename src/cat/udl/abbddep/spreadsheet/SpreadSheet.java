package cat.udl.abbddep.spreadsheet;


import cat.udl.abbddep.cell.Cell;
import cat.udl.abbddep.cell.NotValidAddressException;
import cat.udl.abbddep.expression.Expression;
import cat.udl.abbddep.expression.operation.Plus;
import cat.udl.abbddep.expression.reference.Reference;
import cat.udl.abbddep.expression.value.SomeValue;
import cat.udl.abbddep.sheet.SheetTable;

public class SpreadSheet {

    private static int SIZE = 5;
    private static final SheetTable SHEET = new SheetTable(SIZE);

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


}
