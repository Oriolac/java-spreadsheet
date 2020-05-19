package cat.udl.abbddep.expression.operation;

import cat.udl.abbddep.expression.Expression;
import cat.udl.abbddep.expression.value.MaybeValue;
import cat.udl.abbddep.expression.value.NoValue;
import cat.udl.abbddep.expression.value.SomeValue;

import java.util.concurrent.ForkJoinPool;

public class Plus extends Operation {
    public Plus(Expression e1, Expression e2) {
        super(e1, e2);
    }

    @Override
    public int operate(int i1, int i2) {
        return i1 + i2;
    }


}
