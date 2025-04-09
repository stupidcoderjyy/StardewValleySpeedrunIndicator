package svsl.recipe;

import svsl.Id;

public class RecipeItem {
    public final Id id;

    public RecipeItem(String id) {
        this.id = Id.of(id);
    }
}
