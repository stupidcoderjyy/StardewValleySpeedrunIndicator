package svsl;

import svsl.registry.Elements;
import svsl.world.StardewValley;

public class Main {
    public static void main(String[] args) {
        StardewValley sv = StardewValley.INSTANCE;
        sv.player.inv.store(Elements.STRAWBERRY, 2);
    }
}
