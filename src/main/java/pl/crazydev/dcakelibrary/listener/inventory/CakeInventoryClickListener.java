package pl.crazydev.dcakelibrary.listener.inventory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;
import pl.crazydev.dcakelibrary.DCakeLibrary;
import pl.crazydev.dcakelibrary.listener.EventListener;
import pl.crazydev.dcakelibrary.listener.RegisterListener;
import pl.crazydev.dcakelibrary.menu.Menu;
import pl.crazydev.dcakelibrary.plugin.PluginComponent;

@RegisterListener
@PluginComponent
public final class CakeInventoryClickListener extends EventListener<DCakeLibrary> {
    public CakeInventoryClickListener(DCakeLibrary plugin) {
        super(plugin);
    }

    @EventHandler
    private void onEvent(InventoryClickEvent event) {
        if(event.getClickedInventory() == null) {
            return;
        }
        if(event.getClickedInventory().getHolder() == null) {
            return;
        }

        InventoryHolder holder = event.getClickedInventory().getHolder();

        if (!(holder instanceof Menu)) {
            return;
        }

        ((Menu) event.getClickedInventory().getHolder()).handle(event);
    }
}
