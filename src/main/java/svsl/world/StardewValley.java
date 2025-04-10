package svsl.world;

import svsl.Container;
import svsl.Id;
import svsl.player.Player;
import svsl.registry.Elements;
import svsl.registry.Recipes;

import java.util.EnumMap;
import java.util.Map;

public class StardewValley extends Container {
    public static final StardewValley INSTANCE = new StardewValley();
    public final Player player;
    private final Map<WorldType, World> worlds = new EnumMap<>(WorldType.class);
    public final Date date;

    private StardewValley() {
        super(Id.ofContainer("sv"), "星露谷");
        for (WorldType wt : WorldType.values()) {
            worlds.put(wt, World.create(this, wt));
        }
        this.date = new Date(1, Season.Spring, 1);
        this.player = new Player(this);
        Elements.build();
        Recipes.build();
    }

    public World getWorld(WorldType type) {
        return worlds.get(type);
    }

    @Override
    public void update() {
        super.update();
        date.forward();
    }
}
