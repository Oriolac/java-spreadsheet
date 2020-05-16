package cat.udl.abbddep.expression.value;

import cat.udl.abbddep.expression.Expression;

public abstract class MaybeValue implements Expression {

    public abstract boolean hasValue();
}
