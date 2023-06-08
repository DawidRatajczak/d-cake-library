package pl.crazydev.dcakelibrary.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class PaginatedInventoryMenu<T extends JavaPlugin> extends PlayerMenu {
    private int currentPage;
    private final ItemStack[][] content = new ItemStack[getPageSize()][getSize()];

    public abstract int getPageSize();

    public void open(int page) {
        if(page > getPageSize()) {
            return;
        }
        currentPage = page;
        setContent();
        Inventory inventory = Bukkit.createInventory(this, getSize(), getName());

        inventory.setContents(content[page]);

        menuOwner.openInventory(inventory);
    }

    public void setSlot(ItemStack icon, int id, int index, int page) {
        content[page][index] = createIcon(icon, id, false);
    }

    protected void setSlot(ItemStack item, int id, int index, int page, boolean hideAttributes) {
        content[page][index] = createIcon(item, id, hideAttributes);
    }

    public void setSlot(ItemStack icon, int index, int page) {
        content[page][index] = icon;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    protected PaginatedInventoryMenu(JavaPlugin plugin, Player menuOwner) {
        super(plugin, menuOwner);
    }

    @Override
    public void open() {
        open(0);
    }
}
