package cat.udl.abbddep.cell;


import cat.udl.abbddep.expression.Expression;
import cat.udl.abbddep.expression.value.MaybeValue;
import cat.udl.abbddep.expression.value.NoValue;

public class Cell {

    Expression exp;
    String address;
    private MaybeValue value = NoValue.INSTANCE;

    public Cell(String address) throws NotValidAddressException {
        this.address = address;
    }

    public Cell(String address, Expression exp) throws NotValidAddressException {
        this(address);
        set(exp);
    }

    public void set(Expression exp) {
        this.exp = exp;
    }


    public MaybeValue evaluate() {
        if (!value.hasValue())
            this.value = exp.evaluate();
        return this.value;
    }
}
