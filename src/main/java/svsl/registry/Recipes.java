package svsl.registry;

import svsl.Tag;
import svsl.machine.barrel.BarrelRecipe;
import svsl.machine.farmland.FarmingRecipe;
import svsl.recipe.Recipe;

public class Recipes {
    public static final Recipe<BarrelRecipe> BARREL = new Recipe<BarrelRecipe>("barrel")
            .register(new BarrelRecipe(Tag.FRUIT, Tag.FRUIT_WINE, 7));

    public static final Recipe<FarmingRecipe> FARMING = new Recipe<FarmingRecipe>("farming")
            .register(new FarmingRecipe(Elements.STRAWBERRY, 8, 4));
}
