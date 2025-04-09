package svsl.player;

import svsl.*;

import java.util.*;

public class Inventory extends Container {
    private final Map<Element, ESlot> slots = new HashMap<>();

    public Inventory(Player p, Id id, String name) {
        super(p, id, name);
    }

    public boolean take(Element e, int val) {
        if (!slots.containsKey(e)) {
            return false;
        }
        ESlot slot = slots.get(e);
        if (!slot.hasEnoughContent(val)) {
            return false;
        }
        slot.shift(-val);
        if (slot.isEmpty()) {
            slots.remove(e);
        }
        return true;
    }

    public void store(Element e, int val) {
        ESlot slot = slots.containsKey(e) ? slots.get(e) : registerSlot(EStack.NULL);
        slot.setStack(EStack.merge(slot.getStack(), e, val));
    }

    public void store(EStack stack) {
        ESlot slot = slots.containsKey(stack.element) ? slots.get(stack.element) : registerSlot(EStack.NULL);
        slot.setStack(EStack.merge(slot.getStack(), stack));
    }
}
