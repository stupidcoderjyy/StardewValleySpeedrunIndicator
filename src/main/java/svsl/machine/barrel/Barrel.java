package svsl.machine.barrel;

import svsl.*;
import svsl.machine.Machine;
import svsl.machine.MachineSet;
import svsl.machine.MachineType;
import svsl.registry.Elements;
import svsl.registry.Recipes;

public class Barrel extends Machine<Barrel> {
    public Barrel(int pos, MachineSet<Barrel> set, MachineType type) {
        super(pos, set, type);
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
        for (Tag t : input.element.tags) {
            var item = Recipes.BARREL.getRecipeItem(t.id.name());
            if (item != null) {
                return item.days();
            }
        }
        throw new RuntimeException("No barrel found");
    }
}
