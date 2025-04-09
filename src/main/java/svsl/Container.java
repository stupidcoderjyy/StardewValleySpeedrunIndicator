package svsl;

import java.util.ArrayList;
import java.util.List;

public class Container {
    public final Id id;
    public final String name;
    protected final List<ESlot> slots = new ArrayList<>();
    protected final Container parent;
    protected final List<Container> children = new ArrayList<>();

    public Container(Container parent, Id id, String name) {
        this.id = id;
        this.name = name;
        this.parent = parent;
        this.parent.children.add(this);
    }

    public Container(Id id, String name) {
        this(null, id, name);
    }

    protected ESlot registerSlot(EStack initialStack) {
        ESlot s = new ESlot(initialStack);
        slots.add(s);
        return s;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("[");
        slots.forEach(slot -> sb.append(id).append('=').append(slot).append(","));
        children.forEach(child -> sb.append(child.toString()).append(","));
        if (!slots.isEmpty()) {
            sb.setLength(sb.length() - 1);
        }
        sb.append("]");
        return sb.toString();
    }

    public void update() {
        children.forEach(Container::update);
    }
}
