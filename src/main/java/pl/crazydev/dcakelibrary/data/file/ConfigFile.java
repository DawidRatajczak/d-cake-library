package pl.crazydev.dcakelibrary.data.file;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class ConfigFile {
    private FileConfiguration config;
    private File configFile;

    public ConfigFile() {
        reloadConfig();
    }

    public static ConfigFile of(OfflinePlayer player) {
        return new PlayerConfigFile(player);
    }

    public abstract String getConfigFileName();

    public abstract File getDataFolder();

    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(getConfigFile());
    }

    public File getConfigFile() {
        createConfigFile();

        return configFile;
    }

    public FileConfiguration getConfig() {
        if(config == null) {
            reloadConfig();
        }

        return config;
    }

    public void saveConfig() {
        try {
            getConfig().save(getConfigFile());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void set(String key, Object object) {
        config.set(key, object);
    }

    public Object get(String key) {
        return config.get(key);
    }

    public int getInt(String key) {
        return config.getInt(key);
    }

    public double getDouble(String key) {
        return config.getDouble(key);
    }

    public boolean getBoolean(String key) {
        return config.getBoolean(key, false);
    }

    public Location getLocation(String key) {
        return config.getLocation(key);
    }

    public OfflinePlayer getOfflinePlayer(String key) {
        return config.getOfflinePlayer(key);
    }

    public ItemStack[] getItemStackArray(String key) {
        return config.getList(key).toArray(new ItemStack[0]);
    }

    public String getString(String key) {
        return config.getString(key);
    }

    public ItemStack getItemStack(String key) {
        return config.getItemStack(key);
    }

    public<T> List<T> getList(String key) {
        return (List<T>) config.getList(key);
    }

    private void createConfigFile() {
        if(getDataFolder() == null) {
            return;
        }
        if(getConfigFileName() == null) {
            return;
        }
        if(!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }
        if(configFile == null) {
            configFile = new File(getDataFolder(), getConfigFileName() + ".yml");
        }
        if(!configFile.exists()) {
            try {
                configFile.createNewFile();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }
}
