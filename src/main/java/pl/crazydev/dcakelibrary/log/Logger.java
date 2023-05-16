package pl.crazydev.dcakelibrary.log;

import org.bukkit.Bukkit;
import pl.crazydev.dcakelibrary.text.TextFormatter;

public class Logger {
    public static void log(Object... messages) {
        for(Object message : messages) {
            if(message == null) {
                continue;
            }
            Bukkit.getConsoleSender().sendMessage(TextFormatter.color("&2[LOG] &a" + message));
        }
    }

    public static void error(Object... messages) {
        for(Object message : messages) {
            if(message == null) {
                continue;
            }
            Bukkit.getConsoleSender().sendMessage(TextFormatter.color("&4[ERROR] &c" + message));
        }
    }

    public static void warning(Object... messages) {
        for(Object message : messages) {
            if(message == null) {
                continue;
            }
            Bukkit.getConsoleSender().sendMessage(TextFormatter.color("&6[WARNING] &e" + message));
        }
    }
}
