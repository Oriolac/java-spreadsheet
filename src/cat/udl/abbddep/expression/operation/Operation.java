package cat.udl.abbddep.expression.operation;

import cat.udl.abbddep.expression.Expression;
import cat.udl.abbddep.expression.value.MaybeValue;
import cat.udl.abbddep.expression.value.NoValue;
import cat.udl.abbddep.expression.value.SomeValue;

public abstract class Operation implements Expression {

    protected final Expression e1;
    protected final Expression e2;

    public Operation(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public abstract int operate(int i1, int i2);

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
}
