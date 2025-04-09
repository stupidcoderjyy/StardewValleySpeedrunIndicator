package svsl.player;

import svsl.ESlot;
import svsl.EStack;
import svsl.Container;
import svsl.Id;
import svsl.registry.Elements;
import svsl.world.StardewValley;
import svsl.world.World;

public class Player extends Container {
    public final ESlot stamina;
    public final Inventory inv;

    public Player(StardewValley sv) {
        super(sv, Id.ofContainer("player"), "玩家");
        stamina = registerSlot(new EStack(Elements.STAMINA, 270));
        inv = new Inventory(this, Id.ofContainer("inv"), "库存");
    }
}
