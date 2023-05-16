package pl.crazydev.dcakelibrary.item.interact;

import org.bukkit.event.player.PlayerInteractEvent;

import java.io.Serializable;

public interface ClickAction extends Serializable {
    void onClick(PlayerInteractEvent event);
}
