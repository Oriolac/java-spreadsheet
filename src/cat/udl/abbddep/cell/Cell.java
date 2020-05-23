package cat.udl.abbddep.cell;


import cat.udl.abbddep.expression.Expression;
import cat.udl.abbddep.expression.value.MaybeValue;
import cat.udl.abbddep.expression.value.NoValue;
import cat.udl.abbddep.expression.value.SomeValue;

import java.util.*;

public class Cell extends Observable implements Observer {

    Expression exp;
    private MaybeValue value;

    public Cell(Expression expr) {
        set(expr);
        value = NoValue.INSTANCE;
    }

    public Cell() {
        this(NoValue.INSTANCE);
    }

    public void set(Expression newExp) {
        Expression lastExp = this.exp;
        this.exp = newExp;
        setChanged();
        notifyObservers();
        changeObservables(lastExp);
    }

    private void changeObservables(Expression lastExp) {
        if (lastExp != null)
            removePreviousObservables(lastExp);
        addNewObservables();
    }

    private void removePreviousObservables(Expression lastExp) {
        List<Cell> observables = new LinkedList<>();
        lastExp.getCellsDependency(observables);
        for (Cell cell : observables) {
            cell.deleteObserver(this);
        }
    }

    private void addNewObservables() {
        List<Cell> observables = new LinkedList<>();
        exp.getCellsDependency(observables);
        for (Cell cell: observables) {
            cell.addObserver(this);
        }
    }


    public MaybeValue evaluate() {
        if (!value.hasValue())
            this.value = exp.evaluate();
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
        Cell cell = (Cell) o;
        this.value = cell.evaluate();
        setChanged();
        notifyObservers();
    }

    public Expression getExp() {
        return exp;
    }
}
