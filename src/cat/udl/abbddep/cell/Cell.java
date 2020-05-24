package cat.udl.abbddep.cell;


import cat.udl.abbddep.expression.Expression;
import cat.udl.abbddep.expression.value.MaybeValue;
import cat.udl.abbddep.expression.value.NoValue;
import cat.udl.abbddep.expression.visitor.ObservablesVisitor;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Cell extends Observable implements Observer {

    Expression exp;
    private MaybeValue value;

    public Cell(Expression expr) {
        set(expr);
        value = expr.evaluate();
    }

    public Cell() {
        this(NoValue.INSTANCE);
    }

    public void set(Expression newExp) {
        Expression lastExp = this.exp;
        this.exp = newExp;
        setValue();
        changeObservables(lastExp);
    }

    private void setValue() {
        this.value = exp.evaluate();
        setChanged();
        notifyObservers();
    }

    private void changeObservables(Expression lastExp) {
        if (lastExp != null)
            removePreviousObservables(lastExp);
        addNewObservables();
    }

    private void removePreviousObservables(Expression lastExp) {
        ObservablesVisitor visitor = new ObservablesVisitor();
        lastExp.accept(visitor);
        List<Cell> observables = visitor.getObservables();
        for (Cell cell : observables) {
            cell.deleteObserver(this);
        }
    }

    private void addNewObservables() {
        ObservablesVisitor visitor = new ObservablesVisitor();
        exp.accept(visitor);
        List<Cell> observables = visitor.getObservables();
        for (Cell cell : observables) {
            cell.addObserver(this);
        }
    }


    public MaybeValue evaluate() {
        return this.value;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "exp=" + exp +
                ", value=" + value +
                '}';
    }

    @Override
    public void update(Observable o, Object arg) {
        if (!(o instanceof Cell))
            throw new IllegalArgumentException();
        setValue();
    }

}
