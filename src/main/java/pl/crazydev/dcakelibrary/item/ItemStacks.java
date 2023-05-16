package pl.crazydev.dcakelibrary.item;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import pl.crazydev.dcakelibrary.DCakeLibrary;
import pl.crazydev.dcakelibrary.log.Logger;
import pl.crazydev.dcakelibrary.text.TextFormatter;

import java.util.*;

public abstract class ItemStacks {
    public static final String IS_CUSTOM_ITEM_NAMESPACE = "isCustomItem";
    public static final String FEED_AMOUNT_NAMESPACE = "feedAmount";
    public static final String CLICK_ACTION_NAMESPACE = "clickAction";

    public static boolean isCustomItemStack(ItemStack item) {
        Optional<Integer> isCustomItemStack = getNbtData(item, IS_CUSTOM_ITEM_NAMESPACE, PersistentDataType.INTEGER);

        return isCustomItemStack.isPresent() ? isCustomItemStack.get() == 1 : false;
    }

    public static <T, Z> Optional<Z> getNbtData(ItemStack item, NamespacedKey key, PersistentDataType<T, Z> type) {
        if (item == null || item.getItemMeta() == null) {
            return Optional.empty();
        }

        PersistentDataContainer data = item.getItemMeta().getPersistentDataContainer();

        return data.has(key, type) ? Optional.ofNullable(data.get(key, type)) : Optional.empty();
    }

    public static <T, Z> Optional<Z> getNbtData(ItemStack item, String namespace, PersistentDataType<T, Z> type) {
        if (item == null || item.getItemMeta() == null) {
            return Optional.empty();
        }

        NamespacedKey key = new NamespacedKey(DCakeLibrary.getPlugin(), namespace);

        return getNbtData(item, key, type);
    }

    public static Map<Enchantment, Integer> getEnchants(ItemStack item) {
        return getEnchants(item.getItemMeta());
    }

    public static Map<Enchantment, Integer> getEnchants(ItemMeta meta) {
        if (meta == null) {
            return new HashMap<>();
        }
        if (meta instanceof EnchantmentStorageMeta bookMeta) {
            return bookMeta.getStoredEnchants();
        }
        return meta.getEnchants();
    }

    public static void addEnchant(ItemStack item, Enchantment enchant, int level) {
        if (item == null) {
            return;
        }
        ItemStackEditor.of(item)
                .addEnchant(enchant, level)
                .finish();
    }

    public static int getEnchantLevel(ItemStack item, Enchantment enchant) {
        return getEnchantLevel(item.getItemMeta(), enchant);
    }

    public static int getEnchantLevel(ItemMeta meta, Enchantment enchant) {
        for (Map.Entry<Enchantment, Integer> entry : ItemStacks.getEnchants(meta).entrySet()) {
            if (entry.getKey().equals(enchant)) {
                Logger.log("Enchant: " + entry.getKey().getKey().getNamespace());
                return entry.getValue();
            }
        }
        return 0;
    }

    public static String constructEnchantName(Enchantment enchant, int level) {
        String enchantName = TextFormatter.color("&7"
                .concat(enchant.getKey().getKey().substring(0, 1).toUpperCase())
                .concat(enchant.getKey().getKey().substring(1)));
        if (level > 1) {
            Logger.log("Enchant level is above 1: " + level);
            enchantName = enchantName
                    .concat(" ")
                    .concat(TextFormatter.toRoman(level));
        }
        Logger.log("Enchant name in method: " + enchantName);
        return enchantName;
    }

    public static boolean isTool(ItemStack item) {
        switch (item.getType()) {
            case WOODEN_AXE:
            case WOODEN_HOE:
            case WOODEN_PICKAXE:
            case WOODEN_SHOVEL:
            case WOODEN_SWORD:
            case STONE_AXE:
            case STONE_HOE:
            case STONE_PICKAXE:
            case STONE_SHOVEL:
            case STONE_SWORD:
            case IRON_AXE:
            case IRON_HOE:
            case IRON_PICKAXE:
            case IRON_SHOVEL:
            case IRON_SWORD:
            case GOLDEN_AXE:
            case GOLDEN_HOE:
            case GOLDEN_PICKAXE:
            case GOLDEN_SHOVEL:
            case GOLDEN_SWORD:
            case DIAMOND_AXE:
            case DIAMOND_HOE:
            case DIAMOND_PICKAXE:
            case DIAMOND_SHOVEL:
            case DIAMOND_SWORD:
            case NETHERITE_AXE:
            case NETHERITE_HOE:
            case NETHERITE_PICKAXE:
            case NETHERITE_SHOVEL:
            case NETHERITE_SWORD:
            case BOW:
            case FLINT_AND_STEEL:
            case SHEARS:
            case CROSSBOW:
            case TRIDENT:
            case SHIELD:
            case FISHING_ROD:
                return true;
        }
        return false;
    }

    public static boolean isArmor(ItemStack item) {
        switch (item.getType()) {
            case DIAMOND_CHESTPLATE:
            case DIAMOND_BOOTS:
            case DIAMOND_HELMET:
            case DIAMOND_LEGGINGS:
            case IRON_BOOTS:
            case IRON_CHESTPLATE:
            case IRON_HELMET:
            case IRON_LEGGINGS:
            case GOLDEN_BOOTS:
            case GOLDEN_CHESTPLATE:
            case GOLDEN_HELMET:
            case GOLDEN_LEGGINGS:
            case LEATHER_BOOTS:
            case LEATHER_CHESTPLATE:
            case LEATHER_HELMET:
            case LEATHER_LEGGINGS:
            case NETHERITE_HELMET:
            case NETHERITE_BOOTS:
            case NETHERITE_CHESTPLATE:
            case NETHERITE_LEGGINGS:
            case TURTLE_HELMET:
                return true;
        }
        return false;
    }
}
