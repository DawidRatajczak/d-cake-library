package pl.crazydev.dcakelibrary.menu;

import org.bukkit.event.inventory.InventoryClickEvent;

public interface Menu {
    void open();
    void handle(InventoryClickEvent clickEvent);
    String getName();
    int getSize();
    void setContent();
}
