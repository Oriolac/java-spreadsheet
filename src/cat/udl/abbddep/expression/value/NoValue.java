package cat.udl.abbddep.expression.value;

public class NoValue extends MaybeValue {

    public static final NoValue INSTANCE = new NoValue();

    private NoValue() {
    }

    @Override
    public boolean hasValue() {
        return false;
    }

    @Override
    public MaybeValue evaluate() {
        return this;
    }
}
