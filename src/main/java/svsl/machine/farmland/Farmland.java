package svsl.machine.farmland;

import svsl.*;
import svsl.machine.Machine;
import svsl.registry.Elements;
import svsl.registry.Recipes;
import svsl.world.StardewValley;
import svsl.world.World;

public class Farmland extends Machine {
    private boolean grown;
    private FarmingRecipe recipe;

    public Farmland(World world) {
        super(world, "farmland", "耕地", 2);
    }

    @Override
    public boolean accept(EStack input) {
        return input.element.hasTag(Tag.SEED) && Recipes.FARMING.hasRecipe(Elements.seedToCrop(input.element).id);
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
        recipe = Recipes.FARMING.getRecipeItem(Elements.seedToCrop(input.element).id);
    }

    @Override
    protected int getDuration(EStack input) {
        FarmingRecipe r = Recipes.FARMING.getRecipeItem(input.element.id);
        if (r.cycle() > 0 && grown) {
            return r.cycle();
        }
        return r.days();
    }

    @Override
    public void update() {
        if (isWorking) {
            if (--daysLeft == 0) {
                isWorking = false;
                StardewValley.INSTANCE.player.inv.store(collect());
                if (grown && recipe.cycle() > 0) {
                    daysLeft = recipe.cycle();
                    isWorking = true;
                    inv[0].setStack(recipe.crop().stack(1));
                }
            }
        }
    }
}
