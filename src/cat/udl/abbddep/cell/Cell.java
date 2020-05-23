package cat.udl.abbddep.cell;


import cat.udl.abbddep.expression.Expression;
import cat.udl.abbddep.expression.value.MaybeValue;
import cat.udl.abbddep.expression.value.NoValue;

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
        this.value = exp.evaluate();
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
        List<Cell> observables = lastExp.getCellsObservables();
        for (Cell cell : observables) {
            cell.deleteObserver(this);
        }
    }

    private void addNewObservables() {
        List<Cell> observables = exp.getCellsObservables();
        for (Cell cell : observables) {
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
        this.value = exp.evaluate();
        setChanged();
        notifyObservers();
    }

}
