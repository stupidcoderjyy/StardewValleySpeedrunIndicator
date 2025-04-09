package svsl.recipe;

import svsl.Id;

import java.util.HashMap;
import java.util.Map;

public class Recipe<R extends RecipeItem> {
    private final Map<Id, R> recipes = new HashMap<>();
    public final Id id;

    public Recipe(String id) {
        this.id = Id.ofRecipe(id);
    }

    public Recipe<R> register(R recipe) {
        if (recipes.containsKey(recipe.id)) {
            throw new IllegalArgumentException("Recipe with id '" + id + "' already exists");
        }
        recipes.put(recipe.id, recipe);
        return this;
    }

    public boolean hasRecipe(Id id) {
        return recipes.containsKey(id);
    }

    public R getRecipeItem(Id id) {
        return recipes.get(id);
    }
}
