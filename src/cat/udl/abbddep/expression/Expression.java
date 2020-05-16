package cat.udl.abbddep.expression;

import cat.udl.abbddep.expression.value.MaybeValue;

public interface Expression {

    MaybeValue evaluate();

}
