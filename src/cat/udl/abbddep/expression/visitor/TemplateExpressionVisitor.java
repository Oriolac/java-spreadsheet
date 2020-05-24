package cat.udl.abbddep.expression.visitor;

import cat.udl.abbddep.expression.Expression;
import cat.udl.abbddep.expression.operation.Operation;
import cat.udl.abbddep.expression.reference.Reference;
import cat.udl.abbddep.expression.value.MaybeValue;

import java.util.HashSet;
import java.util.Set;

public abstract class TemplateExpressionVisitor implements ExpressionVisitor{

    private final Set<Expression> visited;

    public TemplateExpressionVisitor() {
        this.visited = new HashSet<>();
    }

    @Override
    public void visit(MaybeValue value) {
        if (!visited.add(value)) return;
        doVisit(value);
    }

    @Override
    public void visit(Operation op) {
        if (!visited.add(op)) return;
        op.getFirstExpression().accept(this);
        op.getSecondExpression().accept(this);
    }

    @Override
    public void visit(Reference r) {
        if (!visited.add(r)) return;
        doVisit(r);
    }

    protected abstract void doVisit(Reference r);

    protected abstract void doVisit(MaybeValue value);
}
