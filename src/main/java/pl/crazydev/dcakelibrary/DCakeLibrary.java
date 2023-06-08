package pl.crazydev.dcakelibrary;

import lombok.Getter;
import pl.crazydev.dcakelibrary.item.CustomItemManager;
import pl.crazydev.dcakelibrary.item.recipe.RecipesManager;
import pl.crazydev.dcakelibrary.plugin.CakePlugin;

import java.io.File;

public final class DCakeLibrary extends CakePlugin {
    public static final String RESOURCE_PACK_LINK = "https://drive.google.com/u/0/uc?id=1lxh4UiLr6aYOqJUNqWBln-wS_2FHwAUg&export=download";
    @Getter
    private final RecipesManager recipesManager = new RecipesManager();
    @Getter
    private final CustomItemManager customItemManager = new CustomItemManager();

    @Override
    public void onEnable() {
        register(this, true);
        saveDefaultConfig();

        recipesManager.unlockRecipesForPlayers();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public File getJarFile() {
        return getFile();
    }

    public static DCakeLibrary getPlugin() {
        return DCakeLibrary.getPlugin(DCakeLibrary.class);
    }
}
