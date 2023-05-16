package pl.crazydev.dcakelibrary.listener.food;

import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import pl.crazydev.dcakelibrary.DCakeLibrary;
import pl.crazydev.dcakelibrary.listener.EventListener;
import pl.crazydev.dcakelibrary.listener.RegisterListener;
import pl.crazydev.dcakelibrary.plugin.PluginComponent;

@RegisterListener
@PluginComponent
public class CakeFoodLevelChangeListener extends EventListener<DCakeLibrary> {
    public CakeFoodLevelChangeListener(DCakeLibrary plugin) {
        super(plugin);
    }

    @EventHandler
    private void onEvent(FoodLevelChangeEvent event) {
        ItemStack food = event.getItem();
        NamespacedKey key = new NamespacedKey(plugin, "feedAmount");

        if(food == null) {
            return;
        }
        if(food.getItemMeta() == null) {
            return;
        }

        PersistentDataContainer data = food.getItemMeta().getPersistentDataContainer();

        if(!data.has(key, PersistentDataType.INTEGER)) {
            return;
        }

        int feedAmount = data.get(key, PersistentDataType.INTEGER) + event.getEntity().getFoodLevel();

        event.setFoodLevel(feedAmount);
    }
}
