package pl.crazydev.dcakelibrary.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.crazydev.dcakelibrary.DCakeLibrary;
import pl.crazydev.dcakelibrary.menu.CustomItemMenu;
import pl.crazydev.dcakelibrary.menu.PaginatedInventoryMenu;
import pl.crazydev.dcakelibrary.message.Msg;
import pl.crazydev.dcakelibrary.number.Numbers;
import pl.crazydev.dcakelibrary.plugin.PluginComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RegisterCommand(name = "customitems")
@PluginComponent
public class CustomItemsCommand extends Command<DCakeLibrary> {
    public CustomItemsCommand(DCakeLibrary plugin) {
        super(plugin);
    }

    @Override
    protected void onPlayerCommand(Player player, org.bukkit.command.Command command, String[] args) {
        if (!player.hasPermission("d-cake-library.custom-items")) {
            Msg.sendMessage(player, Msg.NO_PERMISSION);
            return;
        }

        if (args.length < 2) {
            Msg.sendMessage(player, Msg.WRONG_COMMAND_USAGE);
            return;
        }
        if ("open".equalsIgnoreCase(args[0]) && !Numbers.isInteger(args[1])) {
            Msg.sendMessage(player, Msg.WRONG_COMMAND_USAGE);
            return;
        }

        switch (args[0]) {
            case "open" -> {
                PaginatedInventoryMenu menu = new CustomItemMenu(plugin, player);
                int page = Integer.parseInt(args[1]) - 1;
                if (page > menu.getPageSize() - 1) {
                    Msg.sendMessage(player, Msg.WRONG_COMMAND_USAGE);
                    return;
                }
                menu.open(page);
            }
            case "get" -> {
                Optional<ItemStack> item = plugin.getCustomItemManager().getCustomItem(args[1]);
                if (item.isEmpty()) {
                    Msg.sendMessage(player, Msg.WRONG_COMMAND_USAGE);
                    return;
                }
                if (player.getInventory().firstEmpty() == -1) {
                    player.getWorld().dropItemNaturally(player.getLocation(), item.get());
                } else {
                    player.getInventory().addItem(item.get());
                }
            }
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (args.length == 1) {
            return List.of(
                    "open",
                    "get"
            );
        }
        if (args.length == 2) {
            List<String> tabComplete = new ArrayList<>();

            if ("open".equalsIgnoreCase(args[0])) {
                for (int i = 0; i <= dCakeLibrary.getCustomItemManager().getCustomItems().size(); i += 54) {
                    tabComplete.add(String.valueOf((i / 54) + 1));
                }
                return tabComplete;
            } else if ("get".equalsIgnoreCase(args[0])) {
                return plugin.getCustomItemManager().getIds().stream().toList();
            }
        }
        return List.of();
    }
}
