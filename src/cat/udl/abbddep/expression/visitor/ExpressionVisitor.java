package cat.udl.abbddep.expression.visitor;

import cat.udl.abbddep.expression.Expression;
import cat.udl.abbddep.expression.operation.Operation;
import cat.udl.abbddep.expression.reference.Reference;
import cat.udl.abbddep.expression.value.MaybeValue;

public interface ExpressionVisitor {

    void visit(MaybeValue value);

    void visit(Operation op);

    void visit(Reference r);
}
