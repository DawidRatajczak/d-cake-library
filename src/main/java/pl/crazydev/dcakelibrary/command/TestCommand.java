package pl.crazydev.dcakelibrary.command;

import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import pl.crazydev.dcakelibrary.DCakeLibrary;
import pl.crazydev.dcakelibrary.item.ItemStacks;
import pl.crazydev.dcakelibrary.log.Logger;
import pl.crazydev.dcakelibrary.plugin.PluginComponent;

@PluginComponent
@RegisterCommand(name="test")
public class TestCommand extends Command<DCakeLibrary>{
    public TestCommand(DCakeLibrary plugin) {
        super(plugin);
    }

    @Override
    protected void onPlayerCommand(Player player, org.bukkit.command.Command command, String[] args) {

    }
}
