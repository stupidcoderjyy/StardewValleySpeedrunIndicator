package svsl.action.actions.player;

import svsl.action.Action;
import svsl.action.ActionType;
import svsl.machine.MachineType;
import svsl.player.Player;
import svsl.world.World;

public class ActionPlow extends Action {
    private final World world;
    private final Player player;

    public ActionPlow(World world, Player player) {
        super(ActionType.PLOW);
        this.world = world;
        this.player = player;
    }

    @Override
    public boolean perform() {
        if (player.stamina.hasEnoughContent(2)) {
            player.stamina.shift(-2);
            world.createMachine(MachineType.Farmland);
            return true;
        }
        return false;
    }
}
