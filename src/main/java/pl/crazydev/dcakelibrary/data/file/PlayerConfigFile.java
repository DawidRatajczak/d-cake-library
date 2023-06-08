package pl.crazydev.dcakelibrary.data.file;

import org.bukkit.OfflinePlayer;
import pl.crazydev.dcakelibrary.DCakeLibrary;

import java.io.File;

public class PlayerConfigFile extends ConfigFile {
    private final String playerId;
    PlayerConfigFile(OfflinePlayer player) {
        playerId = player.getUniqueId().toString();
    }
    @Override
    public String getConfigFileName() {
        return playerId;
    }

    @Override
    public File getDataFolder() {
        return new File(DCakeLibrary.getPlugin().getDataFolder().getPath().concat(File.separator).concat("player-data"));
    }
}
