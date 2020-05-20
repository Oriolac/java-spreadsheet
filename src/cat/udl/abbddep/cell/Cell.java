package cat.udl.abbddep.cell;


import cat.udl.abbddep.expression.Expression;
import cat.udl.abbddep.expression.value.MaybeValue;
import cat.udl.abbddep.expression.value.NoValue;

public class Cell {

    Expression exp;
    private MaybeValue value;

    public Cell(Expression expr) {
        set(expr);
        value = NoValue.INSTANCE;
    }

    public Cell() {
        this(NoValue.INSTANCE);
    }

    public void set(Expression exp) {
        this.exp = exp;
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
}
