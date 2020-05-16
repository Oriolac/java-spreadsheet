package cat.udl.abbddep.expression.operation;

import cat.udl.abbddep.expression.Expression;

public abstract class Operation implements Expression {

    private final Expression e1;
    private final Expression e2;

    public Operation(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public abstract int operate(int i1, int i2);

}
