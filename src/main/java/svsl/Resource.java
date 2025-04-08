package svsl;

public class DiscreteResource {
    private int count;
    private final Id id;

    public DiscreteResource(Id id, int initialCount) {
        this.id = id;
        count = initialCount;
    }

    public DiscreteResource(Id id) {
        this(id, 1);
    }

    public int getCount() {
        return count;
    }

    public Id getId() {
        return id;
    }
}
