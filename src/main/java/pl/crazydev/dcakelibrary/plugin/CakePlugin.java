package pl.crazydev.dcakelibrary.plugin;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import pl.crazydev.dcakelibrary.DCakeLibrary;
import pl.crazydev.dcakelibrary.command.Command;
import pl.crazydev.dcakelibrary.command.RegisterCommand;
import pl.crazydev.dcakelibrary.enchant.Enchantments;
import pl.crazydev.dcakelibrary.enchant.RegisterEnchants;
import pl.crazydev.dcakelibrary.item.CustomItems;
import pl.crazydev.dcakelibrary.listener.RegisterListener;
import pl.crazydev.dcakelibrary.log.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public abstract class CakePlugin extends JavaPlugin {
    protected DCakeLibrary dCakeLibrary;

    public abstract File getJarFile();

    public void register(CakePlugin plugin, boolean debug) {
        long currentTime = System.currentTimeMillis();
        dCakeLibrary = DCakeLibrary.getPlugin(DCakeLibrary.class);

        try {
            ZipInputStream zip = new ZipInputStream(new FileInputStream(plugin.getJarFile()));

            for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
                if (entry.isDirectory() || !entry.getName().endsWith(".class")) {
                    continue;
                }

                String name = entry.getName().replace('/', '.');
                name = name.substring(0, name.length() - ".class".length());

                try {
                    Class clazz = Class.forName(name);

                    if (!clazz.isAnnotationPresent(PluginComponent.class)) {
                        continue;
                    }

                    if (clazz.isAnnotationPresent(RegisterListener.class)) {
                        Listener listener = (Listener) clazz.getDeclaredConstructor(plugin.getClass()).newInstance(plugin);
                        Bukkit.getPluginManager().registerEvents(listener, plugin);

                        if (debug) {
                            Logger.log("Registered listener - ".concat(name));
                        }
                    }

                    if (clazz.isAnnotationPresent(RegisterCommand.class)) {
                        Command command = (Command) clazz.getDeclaredConstructor(plugin.getClass()).newInstance(plugin);
                        String commandName = command.getClass().getAnnotation(RegisterCommand.class).name();
                        plugin.getCommand(commandName).setExecutor(command);

                        if (debug) {
                            Logger.log("Registered command - ".concat(name));
                        }
                    }

                    if (clazz.isAnnotationPresent(CustomItems.class)) {
                        for (Field field : clazz.getDeclaredFields()) {
                            if (field.getType() == ItemStack.class) {
                                dCakeLibrary.getCustomItemManager().addCustomItem((ItemStack) field.get(ItemStack.class), field.getName());
                            }
                            if (debug) {
                                Logger.log("Added custom item - ".concat(field.getName()));
                            }
                        }
                    }

                    if (clazz.isAnnotationPresent(RegisterEnchants.class)) {
                        for (Field field : clazz.getDeclaredFields()) {
                            if (field.getType() == Enchantment.class) {
                                Enchantments.registerEnchant((Enchantment) field.get(Enchantment.class));
                            }
                            if (debug) {
                                Logger.log("Registered enchant - ".concat(field.getName()));
                            }
                        }
                    }
                } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException |
                         IllegalAccessException |
                         InvocationTargetException exception) {
                    Logger.error("Failed to load class ".concat(name).concat(" from ").concat(plugin.getName()));

                    exception.printStackTrace();
                }
            }
        } catch (IOException exception) {
            Logger.error("Failed to load classes from ".concat(plugin.getName()));
        }
        Logger.log("Registered ".concat(plugin.getName()).concat(" in ").concat(String.valueOf(System.currentTimeMillis() - currentTime)).concat(" milliseconds"));
    }
}
