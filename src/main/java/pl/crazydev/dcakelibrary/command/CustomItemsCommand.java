package pl.crazydev.dcakelibrary.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.crazydev.dcakelibrary.DCakeLibrary;
import pl.crazydev.dcakelibrary.menu.CustomItemMenu;
import pl.crazydev.dcakelibrary.menu.PaginatedInventoryMenu;
import pl.crazydev.dcakelibrary.message.Msg;
import pl.crazydev.dcakelibrary.number.Numbers;
import pl.crazydev.dcakelibrary.plugin.PluginComponent;

import java.util.ArrayList;
import java.util.List;

@RegisterCommand(name = "customitems")
@PluginComponent
public class CustomItemsCommand extends Command<DCakeLibrary> {
    public CustomItemsCommand(DCakeLibrary plugin) {
        super(plugin);
    }

    @Override
    protected void onPlayerCommand(Player player, org.bukkit.command.Command command, String[] args) {
        if(!player.hasPermission("d-cake-library.custom-items")) {
            Msg.sendMessage(player, Msg.NO_PERMISSION);
            return;
        }

        if(args.length < 2 || !"open".equalsIgnoreCase(args[0]) || !Numbers.isInteger(args[1])) {
            Msg.sendMessage(player, Msg.WRONG_COMMAND_USAGE);
            return;
        }

        PaginatedInventoryMenu menu = new CustomItemMenu(plugin, player);
        int page = Integer.parseInt(args[1]) - 1;

        if(page > menu.getPageSize() - 1) {
            Msg.sendMessage(player, Msg.WRONG_COMMAND_USAGE);
            return;
        }

        menu.open(page);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if(args.length == 1) {
            return List.of(
                    "open"
            );
        }
        if(args.length == 2) {
            List<String> tabComplete = new ArrayList<>();

            for(int i = 0; i <= dCakeLibrary.getCustomItemManager().getCustomItems().size(); i += 54) {
                tabComplete.add(String.valueOf((i / 54) + 1));
            }
            return tabComplete;
        }
        return List.of();
    }
}
