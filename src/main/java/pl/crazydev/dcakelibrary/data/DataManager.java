package pl.crazydev.dcakelibrary.data;

import org.bukkit.OfflinePlayer;
import pl.crazydev.dcakelibrary.DCakeLibrary;
import pl.crazydev.dcakelibrary.data.file.configFile.ConfigFile;

import java.io.File;

public class DataManager {
    public ConfigFile getPlayerData(OfflinePlayer player) {
        return getConfigFile(player.getUniqueId().toString(), DCakeLibrary.getPlugin().getDataFolder().getPath() + File.separator + "data");
    }

    public ConfigFile getServerData() {
        return getConfigFile("serverData", DCakeLibrary.getPlugin().getDataFolder().getPath() + File.separator + "data");
    }

    public ConfigFile getConfigFile(String name, File path) {
        return new ConfigFile() {
            @Override
            public String getConfigFileName() {
                return name;
            }

            @Override
            public File getDataFolder() {
                return path;
            }
        };
    }

    public ConfigFile getConfigFile(String name, String path) {
        return new ConfigFile() {
            @Override
            public String getConfigFileName() {
                return name;
            }

            @Override
            public File getDataFolder() {
                return new File(path);
            }
        };
    }
}
