package cat.udl.abbddep.expression.observer;

import cat.udl.abbddep.cell.Cell;
import cat.udl.abbddep.expression.Expression;
import cat.udl.abbddep.expression.visitor.ObservablesVisitor;

import java.util.List;

public class FacadeVisitor {

    static List<Cell> getCellsObservables(Expression expr) {
        ObservablesVisitor visitor = new ObservablesVisitor();
        expr.accept(visitor);
        return visitor.getObservables();
    }
}
