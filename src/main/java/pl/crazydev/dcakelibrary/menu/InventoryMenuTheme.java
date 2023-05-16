package pl.crazydev.dcakelibrary.menu;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum InventoryMenuTheme {
    DARK(Material.GRAY_STAINED_GLASS_PANE),
    SUPER_DARK(Material.BLACK_STAINED_GLASS_PANE),
    LIGHT(Material.WHITE_STAINED_GLASS_PANE),
    FOREST(Material.GREEN_STAINED_GLASS_PANE),
    SUN(Material.YELLOW_STAINED_GLASS_PANE),
    LAVA(Material.ORANGE_STAINED_GLASS_PANE),
    PIG(Material.PINK_STAINED_GLASS_PANE);

    InventoryMenuTheme(Material background) {
        this.background = new ItemStack(background);
    }
    public final ItemStack background;
}
