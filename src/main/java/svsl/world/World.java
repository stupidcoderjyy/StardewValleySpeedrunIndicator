package svsl.world;

import svsl.Container;
import svsl.machine.Machine;
import svsl.machine.MachineSet;
import svsl.machine.MachineType;
import svsl.machine.barrel.Barrel;
import svsl.machine.farmland.Farmland;

import java.util.*;

public class World extends Container {
    public final WorldType type;
    protected final Map<MachineType, MachineSet<?>> machines = new EnumMap<>(MachineType.class);

    public static World create(StardewValley sv, WorldType type) {
        return new World(sv, type);
    }

    private World(StardewValley sv, WorldType type) {
        super(sv, type.id, type.name);
        this.type = type;
        registerMachine(MachineType.Farmland, Farmland::new);
        registerMachine(MachineType.Barrel, Barrel::new);
    }

    private <M extends Machine<M>> void registerMachine(MachineType type, MachineSet.Builder<M> mb) {
        machines.put(type, new MachineSet<>(this, type, mb));
    }

    public <M extends Machine<M>> MachineSet<M> getMachine(MachineType type) {
        return (MachineSet<M>) machines.get(type);
    }

    public void createMachine(MachineType type) {
        machines.get(type).createMachine();
    }

    public void createMachine(MachineType type, int count) {
        for (int i = 0; i < count; i++) {
            machines.get(type).createMachine();
        }
    }
}
