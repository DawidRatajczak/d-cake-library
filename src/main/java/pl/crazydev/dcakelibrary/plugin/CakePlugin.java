package pl.crazydev.dcakelibrary.plugin;

import org.bukkit.Bukkit;
import org.bukkit.Keyed;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.java.JavaPlugin;
import pl.crazydev.dcakelibrary.DCakeLibrary;
import pl.crazydev.dcakelibrary.command.Command;
import pl.crazydev.dcakelibrary.command.RegisterCommand;
import pl.crazydev.dcakelibrary.enchant.Enchantments;
import pl.crazydev.dcakelibrary.enchant.RegisterEnchants;
import pl.crazydev.dcakelibrary.item.CustomItems;
import pl.crazydev.dcakelibrary.item.recipe.RegisterRecipes;
import pl.crazydev.dcakelibrary.listener.RegisterListener;
import pl.crazydev.dcakelibrary.log.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public abstract class CakePlugin extends JavaPlugin {
    public abstract File getJarFile();

    public void register(CakePlugin plugin, boolean debug) {
        long currentTime = System.currentTimeMillis();

        try {
            ZipInputStream zip = new ZipInputStream(new FileInputStream(plugin.getJarFile()));

            for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
                if (entry.isDirectory() || !entry.getName().endsWith(".class")) {
                    continue;
                }

                String name = constructClassName(entry.getName());
                Class clazz = Class.forName(name);

                if (!clazz.isAnnotationPresent(PluginComponent.class)) {
                    continue;
                }

                registerListener(clazz, plugin, debug);
                registerCommand(clazz, plugin, debug);
                registerCustomItems(clazz, debug);
                registerEnchants(clazz, debug);
                registerRecipes(clazz, debug);
            }
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | InstantiationException |
                IllegalAccessException |
                InvocationTargetException exception) {
            Logger.error("Failed to load classes from ".concat(plugin.getName()));
            exception.printStackTrace();
        }
        Logger.log("Registered "
                .concat(plugin.getName())
                .concat(" in ")
                .concat(String.valueOf(System.currentTimeMillis() - currentTime))
                .concat(" milliseconds"));
    }

    private void registerListener(Class clazz, CakePlugin plugin, boolean debug) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (!clazz.isAnnotationPresent(RegisterListener.class)) {
            return;
        }
        Listener listener = (Listener) clazz.getDeclaredConstructor(plugin.getClass()).newInstance(plugin);
        Bukkit.getPluginManager().registerEvents(listener, plugin);

        if (debug) {
            Logger.log("Registered listener - ".concat(clazz.getName()));
        }
    }

    private void registerCommand(Class clazz, CakePlugin plugin, boolean debug) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (!clazz.isAnnotationPresent(RegisterCommand.class)) {
            return;
        }

        Command command = (Command) clazz.getDeclaredConstructor(plugin.getClass()).newInstance(plugin);
        String commandName = command.getClass().getAnnotation(RegisterCommand.class).name();
        plugin.getCommand(commandName).setExecutor(command);

        if (debug) {
            Logger.log("Registered command - ".concat(clazz.getName()));
        }
    }

    private void registerCustomItems(Class clazz, boolean debug) throws IllegalAccessException {
        if (!clazz.isAnnotationPresent(CustomItems.class)) {
            return;
        }
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getType() != ItemStack.class) {
                continue;
            }

            ItemStack customItem = (ItemStack) field.get(ItemStack.class);
            DCakeLibrary.getPlugin().getCustomItemManager().addCustomItem(customItem, field.getName());

            if (debug) {
                Logger.log("Added custom item - "
                        .concat(field.getName())
                        .concat(customItem.getItemMeta().hasCustomModelData() ? " with custom model data: "
                                .concat(String.valueOf(customItem.getItemMeta().getCustomModelData()))
                                .concat(" for material: ")
                                .concat(customItem.getType().name()) : ""));
            }
        }
    }

    private void registerEnchants(Class clazz, boolean debug) throws IllegalAccessException {
        if (!clazz.isAnnotationPresent(RegisterEnchants.class)) {
            return;
        }
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getType() != Enchantment.class) {
                continue;
            }

            Enchantments.registerEnchant((Enchantment) field.get(Enchantment.class));

            if (debug) {
                Logger.log("Registered enchant - ".concat(field.getName()));
            }
        }
    }

    private void registerRecipes(Class clazz, boolean debug) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (!clazz.isAnnotationPresent(RegisterRecipes.class)) {
            return;
        }

        Object recipes = clazz.getDeclaredConstructor().newInstance();

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.getReturnType() != Recipe.class) {
                continue;
            }
            Recipe recipe = (Recipe) method.invoke(recipes);
            DCakeLibrary.getPlugin().getRecipesManager().registerRecipe(recipe, ((Keyed) recipe).getKey());

            Logger.log("Registered recipe - ".concat(((Keyed) recipe).getKey().getKey()));
        }
    }

    private String constructClassName(String rawName) {
        String name = rawName.replace('/', '.');
        return name.substring(0, name.length() - 6);
    }
}
