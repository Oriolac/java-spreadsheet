package cat.udl.abbddep.expression.value;

public class SomeValue extends MaybeValue {
    final private int value;

    public SomeValue(int value) {
        this.value = value;
    }

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

    @Override
    public String toString() {
        return "SomeValue{" +
                "value=" + value +
                '}';
    }
}
