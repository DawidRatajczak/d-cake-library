package pl.crazydev.dcakelibrary.item.recipe;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipesManager {
    private final List<NamespacedKey> registeredRecipes = new ArrayList<>();

    public void registerRecipe(Recipe recipe, NamespacedKey key) {
        Bukkit.addRecipe(recipe);
        registeredRecipes.add(key);
    }

    public void unlockRecipesForPlayers() {
        Bukkit.getOnlinePlayers()
                .forEach(this::unlockRecipesForPlayer);
    }

    public void unlockRecipesForPlayer(Player player) {
        player.discoverRecipes(registeredRecipes);
    }
}
