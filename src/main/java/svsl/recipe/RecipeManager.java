package svsl.recipe;

import svsl.Id;

import java.util.HashMap;
import java.util.Map;

public class RecipeManager {
    private final Map<Id, Recipe<?>> recipes = new HashMap<>();

    public <C extends RecipeItem, R extends Recipe<C>> R getRecipe(Id id) {
        return (R) recipes.get(id);
    }

    public void registerRecipe(Recipe<?> recipe) {
        if (recipes.containsKey(recipe.id)) {
            throw new IllegalArgumentException("Recipe with id " + recipe.id + " already exists");
        }
        recipes.put(recipe.id, recipe);
    }
}
