package pl.crazydev.dcakelibrary.item;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import pl.crazydev.dcakelibrary.DCakeLibrary;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class ItemStacks {
    public static final String IS_CUSTOM_ITEM_NAMESPACE = "is_custom_item";
    public static final String FEED_AMOUNT_NAMESPACE = "feed_amount";
    public static final String CLICK_ACTION_NAMESPACE = "click_action";
    public static final String EAT_ACTION_NAMESPACE = "eat_action";

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
                return entry.getValue();
            }
        }
        return 0;
    }

    public static boolean isTool(ItemStack item) {
        return switch (item.getType()) {
            case WOODEN_AXE, WOODEN_HOE, WOODEN_PICKAXE, WOODEN_SHOVEL, WOODEN_SWORD, STONE_AXE, STONE_HOE, STONE_PICKAXE, STONE_SHOVEL, STONE_SWORD, IRON_AXE, IRON_HOE, IRON_PICKAXE, IRON_SHOVEL, IRON_SWORD, GOLDEN_AXE, GOLDEN_HOE, GOLDEN_PICKAXE, GOLDEN_SHOVEL, GOLDEN_SWORD, DIAMOND_AXE, DIAMOND_HOE, DIAMOND_PICKAXE, DIAMOND_SHOVEL, DIAMOND_SWORD, NETHERITE_AXE, NETHERITE_HOE, NETHERITE_PICKAXE, NETHERITE_SHOVEL, NETHERITE_SWORD, BOW, FLINT_AND_STEEL, SHEARS, CROSSBOW, TRIDENT, SHIELD, FISHING_ROD ->
                    true;
            default -> false;
        };
    }

    public static boolean isArmor(ItemStack item) {
        return switch (item.getType()) {
            case DIAMOND_CHESTPLATE, DIAMOND_BOOTS, DIAMOND_HELMET, DIAMOND_LEGGINGS, IRON_BOOTS, IRON_CHESTPLATE, IRON_HELMET, IRON_LEGGINGS, GOLDEN_BOOTS, GOLDEN_CHESTPLATE, GOLDEN_HELMET, GOLDEN_LEGGINGS, LEATHER_BOOTS, LEATHER_CHESTPLATE, LEATHER_HELMET, LEATHER_LEGGINGS, NETHERITE_HELMET, NETHERITE_BOOTS, NETHERITE_CHESTPLATE, NETHERITE_LEGGINGS, TURTLE_HELMET ->
                    true;
            default -> false;
        };
    }
}
