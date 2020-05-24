package cat.udl.abbddep.expression.visitor;

import cat.udl.abbddep.cell.Cell;
import cat.udl.abbddep.expression.reference.Reference;
import cat.udl.abbddep.expression.value.MaybeValue;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ObservablesVisitor extends TemplateExpressionVisitor {

    List<Cell> observables;

    public ObservablesVisitor() {
        super();
        this.observables = new LinkedList<>();
    }

    @Override
    protected void doVisit(Reference r) {
        observables.add(r.getCell());
    }

    @Override
    protected void doVisit(MaybeValue value) {
    }

    public List<Cell> getObservables() {
        return Collections.unmodifiableList(observables);
    }

}
