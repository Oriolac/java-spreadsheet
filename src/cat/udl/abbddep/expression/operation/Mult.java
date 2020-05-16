package cat.udl.abbddep.expression.operation;

import cat.udl.abbddep.expression.Expression;
import cat.udl.abbddep.expression.value.MaybeValue;

public class Mult extends Operation {
    public Mult(Expression e1, Expression e2) {
        super(e1, e2);
    }

    @Override
    public int operate(int i1, int i2) {
        return i1 * i2;
    }

    @Override
    public MaybeValue evaluate() {
        return null;
    }
}
