package svsl.machine.farmland;

import svsl.Element;
import svsl.recipe.RecipeItem;
import svsl.registry.Elements;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class FarmingRecipe extends RecipeItem {
    private final Element crop;
    private final int days;
    private final int cycle;

    public FarmingRecipe(Element crop, int days, int cycle) {
        super(crop.id.name());
        this.crop = crop;
        this.days = days;
        this.cycle = cycle;
    }

    public FarmingRecipe(Element crop, int days) {
        this(crop, days, -1);
    }

    public Element crop() {
        return crop;
    }

    public int days() {
        return days;
    }

    public int cycle() {
        return cycle;
    }
}
