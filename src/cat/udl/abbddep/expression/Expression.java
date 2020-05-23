package cat.udl.abbddep.expression;

import cat.udl.abbddep.cell.Cell;
import cat.udl.abbddep.expression.value.MaybeValue;

import java.util.List;

public interface Expression {

    MaybeValue evaluate();

    void getCellsDependency(List<Cell> cells);

}
