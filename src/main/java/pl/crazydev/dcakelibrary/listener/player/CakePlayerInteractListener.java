package pl.crazydev.dcakelibrary.listener.player;

import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.TileState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import pl.crazydev.dcakelibrary.DCakeLibrary;
import pl.crazydev.dcakelibrary.data.persistence.nbt.CustomPersistentDataType;
import pl.crazydev.dcakelibrary.item.ItemStacks;
import pl.crazydev.dcakelibrary.item.action.ClickAction;
import pl.crazydev.dcakelibrary.listener.EventListener;
import pl.crazydev.dcakelibrary.listener.RegisterListener;
import pl.crazydev.dcakelibrary.plugin.PluginComponent;

import java.util.Optional;


@RegisterListener
@PluginComponent
public class CakePlayerInteractListener extends EventListener<DCakeLibrary> {
    public CakePlayerInteractListener(DCakeLibrary plugin) {
        super(plugin);
    }

    @EventHandler
    private void onEvent(PlayerInteractEvent event) {
        ItemStack mainHand = event.getPlayer().getInventory().getItemInMainHand();
        NamespacedKey key = new NamespacedKey(plugin, ItemStacks.CLICK_ACTION_NAMESPACE);

        if(mainHand.getItemMeta() != null) {
            Optional<ClickAction> clickActionOptional = ItemStacks.getNbtData(mainHand, ItemStacks.CLICK_ACTION_NAMESPACE, CustomPersistentDataType.CLICK_ACTION);

            clickActionOptional.ifPresent(clickAction -> clickAction.onClick(event));
        }

        Block block = event.getClickedBlock();

        if(block != null) {
            if(block.getState() instanceof TileState state) {
                PersistentDataContainer blockData = state.getPersistentDataContainer();

                if(blockData.has(key, CustomPersistentDataType.CLICK_ACTION)) {
                    blockData.get(key, CustomPersistentDataType.CLICK_ACTION).onClick(event);
                }
            }
        }
    }
}
