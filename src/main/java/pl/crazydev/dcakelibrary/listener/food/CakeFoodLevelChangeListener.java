package pl.crazydev.dcakelibrary.listener.food;

import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import pl.crazydev.dcakelibrary.DCakeLibrary;
import pl.crazydev.dcakelibrary.data.persistence.nbt.CustomPersistentDataType;
import pl.crazydev.dcakelibrary.data.persistence.nbt.NbtContainer;
import pl.crazydev.dcakelibrary.item.ItemStacks;
import pl.crazydev.dcakelibrary.item.action.EatAction;
import pl.crazydev.dcakelibrary.listener.EventListener;
import pl.crazydev.dcakelibrary.listener.RegisterListener;
import pl.crazydev.dcakelibrary.plugin.PluginComponent;

import java.util.Optional;

@RegisterListener
@PluginComponent
public class CakeFoodLevelChangeListener extends EventListener<DCakeLibrary> {
    public CakeFoodLevelChangeListener(DCakeLibrary plugin) {
        super(plugin);
    }

    @EventHandler
    private void onEvent(FoodLevelChangeEvent event) {
        ItemStack food = event.getItem();

        if (food == null) {
            return;
        }
        if (food.getItemMeta() == null) {
            return;
        }

        NbtContainer container = NbtContainer.get(food);

        Optional<Integer> feedAmount = container.get(ItemStacks.FEED_AMOUNT_NAMESPACE, PersistentDataType.INTEGER);
        Optional<EatAction> eatAction = container.get(ItemStacks.EAT_ACTION_NAMESPACE, CustomPersistentDataType.EAT_ACTION);

        feedAmount.ifPresent(integer -> event.setFoodLevel(integer + event.getFoodLevel()));

        eatAction.ifPresent(action -> action.onEat(event));
    }
}
