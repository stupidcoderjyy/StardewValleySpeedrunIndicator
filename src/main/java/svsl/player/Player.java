package svsl.player;

import svsl.*;
import svsl.action.ActionType;
import svsl.action.actions.player.ActionBrew;
import svsl.action.actions.player.ActionPlow;
import svsl.machine.MachineType;
import svsl.registry.Elements;
import svsl.world.StardewValley;
import svsl.world.World;
import svsl.world.WorldType;

public class Player extends Container {
    public final ESlot stamina;
    public final Inventory inv;
    private World world;
    private Element brewIngredient;

    public Player(StardewValley sv) {
        super(sv, Id.ofContainer("player"), "玩家");
        stamina = registerSlot(new EStack(Elements.STAMINA, 270));
        inv = new Inventory(this, Id.ofContainer("inv"), "库存");
        world = sv.getWorld(WorldType.PELICAN_TOWN);
        registerAction(ActionType.PLOW, () -> new ActionPlow(world, this));
        registerAction(ActionType.BREW, () -> new ActionBrew(inv, brewIngredient, world.getMachine(MachineType.Barrel)));
    }

    public boolean brew(Element ingredient) {
        brewIngredient = ingredient;
        return performAction(ActionType.BREW);
    }

    public int brew(EStack stack) {
        brewIngredient = stack.element;
        return performAction(ActionType.BREW, stack.val);
    }

    public World world() {
        return world;
    }
}
