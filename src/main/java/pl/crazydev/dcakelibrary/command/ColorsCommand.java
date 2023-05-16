package pl.crazydev.dcakelibrary.command;

import lombok.SneakyThrows;
import org.bukkit.entity.Player;
import pl.crazydev.dcakelibrary.DCakeLibrary;
import pl.crazydev.dcakelibrary.message.Msg;
import pl.crazydev.dcakelibrary.plugin.PluginComponent;
import pl.crazydev.dcakelibrary.text.Hex;

import java.lang.reflect.Field;

@RegisterCommand(name = "colors")
@PluginComponent
public class ColorsCommand extends Command<DCakeLibrary>{
    public ColorsCommand(DCakeLibrary plugin) {
        super(plugin);
    }

    @SneakyThrows
    @Override
    protected void onPlayerCommand(Player player, org.bukkit.command.Command command, String[] args) {
        if(!player.hasPermission("d-cake-library.colors")) {
            Msg.sendMessage(player, Msg.NO_PERMISSION);
            return;
        }

        for(Field field : Hex.class.getDeclaredFields()) {
            Msg.sendMessage(player, field.get(String.class) + field.getName().toLowerCase().replace("_", " "));
        }
    }
}
