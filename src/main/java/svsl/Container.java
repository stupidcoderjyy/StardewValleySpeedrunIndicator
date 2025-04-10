package svsl;

import svsl.action.Action;
import svsl.action.ActionType;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

public class Container {
    public final Id id;
    public final String name;
    protected final List<ESlot> slots = new ArrayList<>();
    protected final Container parent;
    protected final List<Container> children = new ArrayList<>();
    protected final Map<ActionType, Supplier<Action>> actions = new EnumMap<>(ActionType.class);
    private final Stack<ESlot> slotPool = new Stack<>();

    public Container(Container parent, Id id, String name) {
        this.id = id;
        this.name = name;
        this.parent = parent;
        if (parent != null) {
            parent.children.add(this);
        }
    }

    public Container(Id id, String name) {
        this(null, id, name);
    }

    protected ESlot registerSlot(EStack initialStack) {
        ESlot s;
        if (slotPool.isEmpty()) {
            s = new ESlot(initialStack);
            slots.add(s);
        } else {
            s = slotPool.pop();
        }
        s.setStack(initialStack);
        return s;
    }

    protected void unregisterSlot(ESlot slot) {
        slot.setStack(EStack.NULL);
        slotPool.push(slot);
    }

    protected void registerAction(ActionType type, Supplier<Action> action) {
        actions.put(type, action);
    }

    public boolean performAction(ActionType type) {
        Container c = this;
        while (c != null) {
            var action = c.actions.get(type);
            if (action == null) {
                c = c.parent;
                continue;
            }
            return action.get().perform();
        }
        throw new IllegalStateException("No action registered for type " + type);
    }

    public int performAction(ActionType type, int times) {
        int pass = 0;
        for (int i = 0; i < times; i++) {
            if (performAction(type)) {
                pass++;
            }
        }
        return pass;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        if (!slots.isEmpty()) {
            sb.append("[");
            slots.forEach(slot -> sb.append(slot).append(","));
            sb.setLength(sb.length() - 1);
            sb.append("]");
        }
        if (!children.isEmpty()) {
            if (!slots.isEmpty()) {
                sb.append(",");
            }
            sb.append("[");
            children.forEach(child -> sb.append(child.toString()).append(","));
            sb.setLength(sb.length() - 1);
            sb.append("]");
        }
        return sb.toString();
    }

    public void update() {
        children.forEach(Container::update);
    }
}