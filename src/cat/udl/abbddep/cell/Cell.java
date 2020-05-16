package cat.udl.abbddep.cell;


import cat.udl.abbddep.expression.Expression;
import cat.udl.abbddep.expression.value.MaybeValue;
import cat.udl.abbddep.expression.value.NoValue;

public class Cell {

    Expression exp;
    String address;
    private MaybeValue value = NoValue.INSTANCE;

    public Cell(String address) throws NotValidAddressException {
        checkAddress(address);
        this.address = address;
    }

    public void set(Expression exp) {
        this.exp = exp;
    }

    private void checkAddress(String address) throws NotValidAddressException {
        if (!address.matches("[a-zA-Z]+[\\d]+"))
            throw new NotValidAddressException();
    }

    public MaybeValue evaluate() {
        if (!value.hasValue())
            this.value = exp.evaluate();
        return this.value;
    }
}
