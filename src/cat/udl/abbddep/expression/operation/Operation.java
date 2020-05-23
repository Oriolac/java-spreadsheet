package cat.udl.abbddep.expression.operation;

import cat.udl.abbddep.cell.Cell;
import cat.udl.abbddep.expression.Expression;
import cat.udl.abbddep.expression.value.MaybeValue;
import cat.udl.abbddep.expression.value.NoValue;
import cat.udl.abbddep.expression.value.SomeValue;

import java.util.LinkedList;
import java.util.List;

public abstract class Operation implements Expression, OperationInt {

    private final Expression e1;
    private final Expression e2;

    public Operation(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public MaybeValue evaluate() {
        MaybeValue mValue1 = e1.evaluate();
        MaybeValue mValue2 = e2.evaluate();
        if (!mValue1.hasValue() || !mValue2.hasValue())
            return NoValue.INSTANCE;
        SomeValue v1 = (SomeValue) mValue1;
        SomeValue v2 = (SomeValue) mValue2;
        return new SomeValue(operate(v1.getValue(), v2.getValue()));
    }

    @Override
    public void addCellsObservables(List<Cell> cells) {
        e1.addCellsObservables(cells);
        e2.addCellsObservables(cells);
    }

    @Override
    public List<Cell> getCellsObservables() {
        List<Cell> cells = new LinkedList<>();
        this.addCellsObservables(cells);
        return cells;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "e1=" + e1 +
                ", e2=" + e2 +
                '}';
    }
}
