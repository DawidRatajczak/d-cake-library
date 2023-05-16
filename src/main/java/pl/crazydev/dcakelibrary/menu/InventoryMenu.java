package pl.crazydev.dcakelibrary.menu;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class InventoryMenu<T extends JavaPlugin> extends PlayerMenu {
    private final ItemStack[] content = new ItemStack[Math.min(getSize(), 54)];
    protected InventoryMenu(T plugin, Player menuOwner) {
        super(plugin, menuOwner);
    }

    protected void setSlot(ItemStack item, int id, int index) {
        content[index] = createIcon(item, id , false);
    }

    protected void setSlot(ItemStack item, int id, int index, boolean hideAttributes) {
        content[index] = createIcon(item, id, hideAttributes);
    }

    protected void setGlowing(int index) {
        if(content[index] == null) {
            return;
        }

        ItemStack icon = content[index];
        ItemMeta meta = icon.getItemMeta();

        meta.getItemFlags().add(ItemFlag.HIDE_ENCHANTS);
        meta.addEnchant(Enchantment.MENDING, 1, true);

        icon.setItemMeta(meta);

        content[index] = icon;
    }

    @Override
    public void open() {
        Inventory inventory = Bukkit.createInventory(this, getSize(), getName());
        setContent();
        inventory.setContents(content);
        menuOwner.openInventory(inventory);
    }
}
