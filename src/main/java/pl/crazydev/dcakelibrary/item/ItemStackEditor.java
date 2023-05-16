package pl.crazydev.dcakelibrary.item;

import com.google.common.collect.Multimap;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import pl.crazydev.dcakelibrary.DCakeLibrary;
import pl.crazydev.dcakelibrary.enchant.Enchantments;
import pl.crazydev.dcakelibrary.log.Logger;
import pl.crazydev.dcakelibrary.text.TextFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ItemStackEditor {
    private ItemStack item;
    private ItemMeta meta;

    private ItemStackEditor(ItemStack item) {
        this.item = item;
        this.meta = item.getItemMeta();
    }

    private ItemStackEditor(ItemStack item, ItemMeta meta) {
        this.meta = meta;
        this.item = item;
    }

    public static ItemStackEditor of(ItemStack item) {
        return new ItemStackEditor(item);
    }

    public static ItemStackEditor of(ItemStack item, ItemMeta meta) {
        return new ItemStackEditor(item, meta);
    }

    public ItemStackEditor setDisplayName(String displayName) {
        meta.setDisplayName(TextFormatter.color(displayName));
        return this;
    }

    public ItemStackEditor setType(Material type) {
        item.setType(type);
        return this;
    }

    public ItemStackEditor addAttributeModifier(Attribute attribute, AttributeModifier modifier) {
        meta.addAttributeModifier(attribute, modifier);
        return this;
    }

    public ItemStackEditor addEnchant(Enchantment enchant, int level) {
        if (Enchantments.isCustomEnchant(enchant)) {
            if (ItemStacks.getEnchants(meta).containsKey(enchant)) {
                removeEnchant(enchant);
            }
            List<String> lore = getLore();
            lore.add(ItemStacks.constructEnchantName(enchant, level));
            meta.setLore(lore);
        }
        if (item.getType() == Material.ENCHANTED_BOOK) {
            ((EnchantmentStorageMeta) meta).addStoredEnchant(enchant, level, true);
        } else {
            meta.addEnchant(enchant, level, true);
        }
        return this;
    }

    public ItemStackEditor addItemFlags(ItemFlag... itemFlags) {
        meta.addItemFlags(itemFlags);
        return this;
    }

    public ItemStackEditor removeAttributeModifier(Attribute attribute) {
        meta.removeAttributeModifier(attribute);
        return this;
    }

    public ItemStackEditor removeAttributeModifier(Attribute attribute, AttributeModifier modifier) {
        meta.removeAttributeModifier(attribute, modifier);
        return this;
    }

    public ItemStackEditor removeAttributeModifier(EquipmentSlot slot) {
        meta.removeAttributeModifier(slot);
        return this;
    }

    public ItemStackEditor removeItemFlags(ItemFlag... itemFlags) {
        meta.removeItemFlags(itemFlags);
        return this;
    }

    public ItemStackEditor setAttributeModifier(Multimap<Attribute, AttributeModifier> attributeModifiers) {
        meta.setAttributeModifiers(attributeModifiers);
        return this;
    }

    public ItemStackEditor setCustomModelData(Integer customModelData) {
        meta.setCustomModelData(customModelData);
        return this;
    }

    public ItemStackEditor setLocalizedName(String name) {
        meta.setLocalizedName(TextFormatter.color(name));
        return this;
    }

    public ItemStackEditor setLore(String... lore) {
        meta.setLore(Stream.of(lore)
                .map(TextFormatter::color)
                .toList());
        return this;
    }

    public ItemStackEditor addLore(String... lore) {
        List<String> newLore = getLore();
        newLore.addAll(Stream.of(lore)
                .map(TextFormatter::color)
                .toList());
        meta.setLore(newLore);
        return this;
    }

    public ItemStackEditor setUnbreakable(boolean unbreakable) {
        meta.setUnbreakable(unbreakable);
        return this;
    }

    public <T, Z> ItemStackEditor setNbtData(NamespacedKey key, PersistentDataType<T, Z> type, Z value) {
        meta.getPersistentDataContainer().set(key, type, value);
        return this;
    }

    public <T, Z> ItemStackEditor setNbtData(String namespace, PersistentDataType<T, Z> type, Z value) {
        meta.getPersistentDataContainer().set(new NamespacedKey(DCakeLibrary.getPlugin(), namespace), type, value);
        return this;
    }

    public ItemStackEditor removeLoreLine(String line) {
        if (!meta.hasLore()) {
            return this;
        }
        List<String> lore = getLore();

        lore.remove(line);
        meta.setLore(lore);
        return this;
    }

    public ItemStackEditor removeLoreLine(int index) {
        if (!meta.hasLore()) {
            return this;
        }
        List<String> lore = getLore();

        lore.remove(index);
        meta.setLore(lore);
        return this;
    }

    public ItemStackEditor removeEnchant(Enchantment enchant) {
        if (!ItemStacks.getEnchants(meta).containsKey(enchant)) {
            return this;
        }
        if (Enchantments.isCustomEnchant(enchant)) {
            String enchantName = ItemStacks.constructEnchantName(enchant, ItemStacks.getEnchantLevel(meta, enchant));
            Logger.log("Removed custom enchant lore: ".concat(enchantName));
            removeLoreLine(enchantName);
        }
        if (meta instanceof EnchantmentStorageMeta bookMeta) {
            bookMeta.removeStoredEnchant(enchant);
        } else {
            meta.removeEnchant(enchant);
        }
        return this;
    }

    public ItemStack finish() {
        item.setItemMeta(meta);
        return item;
    }

    public ItemMeta getItemMeta() {
        return meta;
    }

    public ItemStack getItem() {
        return item;
    }

    private List<String> getLore() {
        return meta.hasLore() ? meta.getLore() : new ArrayList<>();
    }
}
