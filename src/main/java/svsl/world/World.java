package svsl.world;

import svsl.Container;

public class World extends Container {
    public final WorldType type;

    private World(StardewValley sv, WorldType type) {
        super(sv, type.id, type.name);
        this.type = type;
    }

    public static World create(StardewValley sv, WorldType type) {
        return new World(sv, type);
    }
}
