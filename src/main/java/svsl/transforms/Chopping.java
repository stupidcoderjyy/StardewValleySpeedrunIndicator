package svsl.transforms;

import svsl.Id;
import svsl.AbstractTransform;
import svsl.registry.Elements;
import svsl.player.Player;

public class Chopping extends AbstractTransform<Player, Player> {
    public Chopping(Id id) {
        super(id);
    }

    @Override
    public boolean accept(Player in) {
        return in.stamina.hasEnoughContent(2);
    }

    @Override
    public void apply(Player in, Player out) {
        in.stamina.shift(-2);
        out.inv.store(Elements.WOOD, 10);
    }
}
