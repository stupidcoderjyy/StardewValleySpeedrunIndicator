package svsl.machine;

import svsl.Container;
import svsl.EStack;
import svsl.Element;
import svsl.world.World;

import java.util.*;
import java.util.function.Supplier;

public class MachineSet<M extends Machine<?>> extends Container {
    private final List<M> machines = new ArrayList<>();
    private final Map<MachineState, Set<Integer>> stateToId = new EnumMap<>(MachineState.class);
    private final Supplier<M> factory;
    private int totalCount;

    public MachineSet(World parent, MachineType type, Builder<M> builder) {
        super(parent, type.id, type.name);
        this.factory = () -> builder.create(totalCount++, this, type);
        for (MachineState v : MachineState.values()) {
            stateToId.put(v, new HashSet<>());
        }
    }

    public void createMachine() {
        M m = factory.get();
        machines.add(m);
        addToStateSet(m, MachineState.Standby);
    }

    public List<EStack> collect() {
        List<EStack> result = new ArrayList<>();
        Set<Integer> finished = new HashSet<>(stateToId.get(MachineState.Finished));
        for (int i : finished) {
            M m = machines.get(i);
            addToStateSet(m, MachineState.Standby);
            result.add(m.collect());
        }
        stateToId.get(MachineState.Finished).clear();
        return result;
    }

    public int countMachine(MachineState state) {
        return stateToId.get(state).size();
    }

    public boolean process(Element e) {
        var m = stateToId.get(MachineState.Standby).stream().findFirst();
        if (m.isEmpty()) {
            return false;
        }
        M machine = machines.get(m.get());
        EStack s = e.stack(1);
        if (!machine.accept(s)) {
            return false;
        }
        machine.start(s);
        return true;
    }

    private void addToStateSet(M m, MachineState s) {
        stateToId.get(s).add(m.pos);
    }

    private void removeFromStateSet(M m, MachineState s) {
        stateToId.get(s).remove(m.pos);
    }

    protected void onStateChanged(M machine, MachineState pre, MachineState cur) {
        removeFromStateSet(machine, pre);
        addToStateSet(machine, cur);
    }

    public interface Builder<M extends Machine<?>> {
        M create(int id, MachineSet<M> set, MachineType type);
    }
}
