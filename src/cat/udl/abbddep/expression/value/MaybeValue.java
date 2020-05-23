package cat.udl.abbddep.expression.value;

import cat.udl.abbddep.cell.Cell;
import cat.udl.abbddep.expression.Expression;

import java.util.List;

public abstract class MaybeValue implements Expression {

    public abstract boolean hasValue();

    @Override
    public void getCellsDependency(List<Cell> cells) {
    }
}
