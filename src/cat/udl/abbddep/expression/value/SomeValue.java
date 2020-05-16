package cat.udl.abbddep.expression.value;

public class SomeValue extends MaybeValue {
    private int value;

    @Override
    public boolean hasValue() {
        return true;
    }

    public int getValue() {
        return value;
    }

    @Override
    public MaybeValue evaluate() {
        return this;
    }
}
