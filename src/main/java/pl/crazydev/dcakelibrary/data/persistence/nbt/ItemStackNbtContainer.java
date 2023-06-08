package pl.crazydev.dcakelibrary.data.persistence.nbt;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;

public class ItemStackNbtContainer extends NbtContainerBase {
    private final ItemStack item;
    private final ItemMeta meta;

    ItemStackNbtContainer(ItemStack item) {
        this.item = item;
        meta = item.getItemMeta();
    }

    @Override
    public void save() {
        item.setItemMeta(meta);
    }

    @Override
    protected PersistentDataContainer getContainer() {
        return meta.getPersistentDataContainer();
    }
}
