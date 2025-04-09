package svsl;

public abstract class AbstractTransform<I extends Container, O extends Container> {
    public final Id id;

    public AbstractTransform(Id id) {
        this.id = id;
    }

    public abstract boolean accept(I in);
    public abstract void apply(I in, O out);
}
