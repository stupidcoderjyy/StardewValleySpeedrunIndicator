package svsl.machine.farmland;

import svsl.*;
import svsl.machine.Machine;
import svsl.machine.MachineSet;
import svsl.machine.MachineState;
import svsl.machine.MachineType;
import svsl.registry.Elements;
import svsl.registry.Recipes;
import svsl.world.StardewValley;
import svsl.world.World;

public class Farmland extends Machine<Farmland> {
    private boolean grown;
    private FarmingRecipe recipe;

    public Farmland(int pos, MachineSet<Farmland> set, MachineType type) {
        super(pos, set, type);
    }

    @Override
    public boolean accept(EStack input) {
        recipe = Recipes.FARMING.getRecipeItem(Elements.seedToCrop(input.element).id.name());
        return input.element.hasTag(Tag.SEED) && recipe != null;
    }

    @Override
    protected void onFinished() {
        if (!grown) {
            inv[0].setStack(recipe.crop().stack(1));
            grown = true;
        }
    }

    @Override
    protected void onStarting(EStack input) {
        inv[0].setStack(input.copy());
    }

    @Override
    protected int getDuration(EStack input) {
        if (recipe.cycle() > 0 && grown) {
            return recipe.cycle();
        }
        return recipe.days();
    }

    @Override
    public void update() {
        if (state == MachineState.Working) {
            if (--daysLeft == 0) {
                setState(MachineState.Finished);
                StardewValley.INSTANCE.player.inv.store(collect());
                if (grown && recipe.cycle() > 0) {
                    daysLeft = recipe.cycle();
                    setState(MachineState.Working);
                    inv[0].setStack(recipe.crop().stack(1));
                }
            }
        }
    }
}
