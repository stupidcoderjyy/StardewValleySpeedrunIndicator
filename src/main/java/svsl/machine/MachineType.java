package svsl.machine;

import svsl.Id;

public enum MachineType {
    Farmland("耕地", 1),
    Barrel("小桶", 1)
    ;
    public final Id id;
    public final String name;
    public final int slots;

    MachineType(String name, int slots) {
        this.id = Id.ofMachine(name().toLowerCase());
        this.name = name;
        this.slots = slots;
    }
}
