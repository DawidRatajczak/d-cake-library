package pl.crazydev.dcakelibrary.menu;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class PlayerMenu<T extends JavaPlugin> implements Menu, InventoryHolder {

    protected final T plugin;
    protected final Player menuOwner;
    protected InventoryMenuTheme theme = InventoryMenuTheme.DARK;

    protected PlayerMenu(T plugin, Player menuOwner) {
        this.plugin = plugin;
        this.menuOwner = menuOwner;
    }

    public int getId(ItemStack icon) {
        NamespacedKey key = new NamespacedKey(plugin, "iconId");

        if(icon == null) {
            return 0;
        }
        if(icon.getItemMeta() == null) {
            return 0;
        }
        if(!icon.getItemMeta().getPersistentDataContainer().has(key, PersistentDataType.INTEGER)) {
            return 0;
        }

        return icon.getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.INTEGER);
    }

    @Override
    public Inventory getInventory() {
        return menuOwner.getInventory();
    }

    protected ItemStack createIcon(ItemStack icon, int id, boolean hideAttributes) {
        ItemMeta meta = icon.getItemMeta();
        NamespacedKey key = new NamespacedKey(plugin, "iconId");

        assert meta != null : "meta not found";
        assert id != 0 : "id can't be 0";

        if(hideAttributes) {
            meta.getItemFlags().add(ItemFlag.HIDE_ATTRIBUTES);
            meta.getItemFlags().add(ItemFlag.HIDE_POTION_EFFECTS);
            meta.getItemFlags().add(ItemFlag.HIDE_DYE);
        }

        meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, id);

        icon.setItemMeta(meta);

        return icon;
    }
}
