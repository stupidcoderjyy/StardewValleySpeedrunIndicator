package svsl.machine.barrel;

import svsl.Tag;
import svsl.recipe.RecipeItem;

public final class BarrelRecipe extends RecipeItem {
    private final Tag in;
    private final Tag out;
    private final int days;

    public BarrelRecipe(Tag in, Tag out, int days) {
        super(in.id.name());
        this.in = in;
        this.out = out;
        this.days = days;
    }

    public Tag in() {
        return in;
    }

    public Tag out() {
        return out;
    }

    public int days() {
        return days;
    }
}
