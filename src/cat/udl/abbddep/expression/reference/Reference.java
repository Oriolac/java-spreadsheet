package cat.udl.abbddep.expression.reference;


import cat.udl.abbddep.cell.Cell;
import cat.udl.abbddep.sheet.NotValidAddressException;
import cat.udl.abbddep.expression.Expression;
import cat.udl.abbddep.expression.value.MaybeValue;

import java.util.LinkedList;
import java.util.List;

public class Reference implements Expression {


    private final Cell cell;

    public Reference(Cell cell) throws NotValidAddressException {
        this.cell = cell;
    }


    @Override
    public MaybeValue evaluate() {
        return cell.evaluate();
    }

    @Override
    public void addCellsObservables(List<Cell> cells) {
        cells.add(cell);
    }

    @Override
    public List<Cell> getCellsObservables() {
        return new LinkedList<>(List.of(cell));
    }

    @Override
    public String toString() {
        return "Reference{" +
                "cell=" + cell +
                '}';
    }
}
