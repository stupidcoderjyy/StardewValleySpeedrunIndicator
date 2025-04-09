package svsl.machine.barrel;

import svsl.*;
import svsl.machine.Machine;
import svsl.registry.Elements;
import svsl.registry.Recipes;
import svsl.world.World;

public class Barrel extends Machine {
    public Barrel(World world) {
        super(world, "barrel", "小桶");
    }

    @Override
    public boolean accept(EStack input) {
        return input.element.hasTag(Tag.CAN_BREW);
    }

    @Override
    protected void onFinished() {
        inv[0].setStack(Elements.brewResult(inv[0].getStack().element).stack(1));
    }

    @Override
    protected void onStarting(EStack input) {
        inv[0].setStack(input);
    }

    @Override
    protected int getDuration(EStack input) {
        return Recipes.BARREL.getRecipeItem(input.element.id).days();
    }
}
