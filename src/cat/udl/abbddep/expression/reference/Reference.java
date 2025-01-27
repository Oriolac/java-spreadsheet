package cat.udl.abbddep.expression.reference;


import cat.udl.abbddep.cell.Cell;
import cat.udl.abbddep.expression.Expression;
import cat.udl.abbddep.expression.value.MaybeValue;
import cat.udl.abbddep.expression.visitor.ExpressionVisitor;
import cat.udl.abbddep.sheet.NotValidAddressException;

public class Reference implements Expression {


    private final Cell cell;

    public Reference(Cell cell) throws NotValidAddressException {
        this.cell = cell;
    }


    @Override
    public MaybeValue evaluate() {
        return cell.evaluate();
    }

    @Override
    public void accept(ExpressionVisitor v) {
        v.visit(this);
    }

    public Cell getCell() {
        return cell;
    }

    @Override
    public String toString() {
        return "Reference{" +
                "cell=" + cell +
                '}';
    }
}
