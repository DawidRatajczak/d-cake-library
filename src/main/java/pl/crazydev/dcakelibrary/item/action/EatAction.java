package pl.crazydev.dcakelibrary.item.action;

import org.bukkit.event.entity.FoodLevelChangeEvent;

import java.io.Serializable;

public interface EatAction extends Serializable {
    void onEat(FoodLevelChangeEvent event);
}
