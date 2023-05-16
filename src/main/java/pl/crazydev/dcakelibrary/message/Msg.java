package pl.crazydev.dcakelibrary.message;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import pl.crazydev.dcakelibrary.text.TextFormatter;

public class Msg {
    public static final String NO_PERMISSION = TextFormatter.color("§4> &cNie posiadasz permisji aby to zrobić.");
    public static final String OPTION_DISABLED = TextFormatter.color("§4> &cFunkcja jest wyłączona.");
    public static final String WRONG_COMMAND_USAGE = TextFormatter.color("§4> &cNieprawidłowe użycie komendy.");

    public static void sendMessage(Player player, String... messages) {
        if (player == null) {
            return;
        }
        if(messages == null ) {
            return;
        }
        for(String message : messages) {
            if(message == null) {
                continue;
            }
            player.sendMessage(TextFormatter.color(message));
        }
    }


    public static void sendPrefixMessage(Player player, String prefixColor, String... messages) {
        for(String message : messages) {
            if(message == null) {
                continue;
            }
            sendMessage(player, prefixColor + "> " + message);
        }
    }

    public static void sendActionBarMessage(Player player, String message){
        if (player == null) {
            return;
        }
        if (message == null) {
            return;
        }
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(TextFormatter.color(message)));
    }
}
