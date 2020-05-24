package cat.udl.abbddep.expression.visitor;

import cat.udl.abbddep.expression.Expression;
import cat.udl.abbddep.expression.operation.Operation;
import cat.udl.abbddep.expression.reference.Reference;
import cat.udl.abbddep.expression.value.MaybeValue;

import java.util.HashSet;
import java.util.Set;

public abstract class TemplateExpressionVisitor implements ExpressionVisitor{

    public TemplateExpressionVisitor() {
    }

    @Override
    public void visit(MaybeValue value) {
        doVisit(value);
    }

    @Override
    public void visit(Operation op) {
        op.getFirstExpression().accept(this);
        op.getSecondExpression().accept(this);
    }

    @Override
    public void visit(Reference r) {
        doVisit(r);
    }

    protected abstract void doVisit(Reference r);

    protected abstract void doVisit(MaybeValue value);
}
