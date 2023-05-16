package pl.crazydev.dcakelibrary.command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import pl.crazydev.dcakelibrary.DCakeLibrary;

import java.util.ArrayList;
import java.util.List;

public abstract class Command<T extends JavaPlugin> implements CommandExecutor, TabCompleter {
    protected final T plugin;
    protected final DCakeLibrary dCakeLibrary;

    public Command(T plugin) {
        this.dCakeLibrary = DCakeLibrary.getPlugin();
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if(sender instanceof Player player) {
            onPlayerCommand(player, command, args);
        } else {
            onOtherCommand(sender, command, args);
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        return new ArrayList<>();
    }
    protected abstract void onPlayerCommand(Player player, org.bukkit.command.Command command, String[] args);

    protected void onOtherCommand(CommandSender sender, org.bukkit.command.Command command, String[] args) {
        sender.sendMessage("Hi console :)");
    }
}
