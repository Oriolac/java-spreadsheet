package cat.udl.abbddep.sheet;

import cat.udl.abbddep.cell.Cell;
import cat.udl.abbddep.expression.Expression;

public class SheetTable implements Sheet{
    private final int size;
    private final Cell[][] cells;


    public SheetTable(int size) {
        this.size = size;
        this.cells = new Cell[size][size];
    }

    @Override
    public void putCell(String address, Expression expr) {

    }

    @Override
    public Cell getCell(String ref) {
        return null;
    }
}
