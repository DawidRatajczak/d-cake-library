package pl.crazydev.dcakelibrary.listener;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import pl.crazydev.dcakelibrary.DCakeLibrary;

public abstract class EventListener<T extends JavaPlugin> implements Listener {
    protected final T plugin;
    protected final DCakeLibrary dCakeLibrary;

    public EventListener(T plugin) {
        this.plugin = plugin;
        this.dCakeLibrary = DCakeLibrary.getPlugin();
    }
}
