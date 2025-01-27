package cat.udl.abbddep.expression.value;

import cat.udl.abbddep.expression.Expression;
import cat.udl.abbddep.expression.visitor.ExpressionVisitor;

public abstract class MaybeValue implements Expression {

    public abstract boolean hasValue();

    @Override
    public void accept(ExpressionVisitor v) {
        v.visit(this);
    }
}
