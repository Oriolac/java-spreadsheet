package cat.udl.abbddep.expression.operation;

import cat.udl.abbddep.expression.value.MaybeValue;

public interface OperationInt {


    public abstract int operate(int i1, int i2);

    default public MaybeValue evaluate2() {
        System.out.println("HI");
        return null;
    }
}
