package svsl;

import svsl.recipe.RecipeManager;

public class Indicator {
    public static final Indicator INSTANCE = new Indicator();
    public final RecipeManager manager;

    public Indicator() {
        manager = new RecipeManager();
    }
}
