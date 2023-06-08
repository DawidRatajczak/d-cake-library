package pl.crazydev.dcakelibrary.item;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataType;
import pl.crazydev.dcakelibrary.data.persistence.nbt.CustomPersistentDataType;
import pl.crazydev.dcakelibrary.item.action.ClickAction;
import pl.crazydev.dcakelibrary.item.action.EatAction;

public class ItemStackBuilder {
    private final ItemStackEditor editor;

    public ItemStackBuilder(Material material) {
        editor = ItemStackEditor.of(new ItemStack(material));
    }

    public <T, Z> ItemStackBuilder setNbtData(String namespace, PersistentDataType<T, Z> type, Z value) {
        editor.setNbtData(namespace, type, value);
        return this;
    }

    public ItemStackBuilder addDamage(int damage) {
        if (editor.getItemMeta() instanceof Damageable damageable) {
            damageable.setDamage(editor.getItem().getType().getMaxDurability() - damage);
        }
        return this;
    }

    public ItemStackBuilder setArmorColor(Color color) {
        if (editor.getItemMeta() instanceof LeatherArmorMeta leatherArmorMeta) {
            leatherArmorMeta.setColor(color);
        }
        return this;
    }

    public ItemStackBuilder addEnchant(Enchantment enchant, int level) {
        editor.addEnchant(enchant, level);
        return this;
    }

    public ItemStackBuilder setDisplayName(String name) {
        editor.setDisplayName(name);
        return this;
    }

    public ItemStackBuilder setLore(String... lore) {
        editor.setLore(lore);
        return this;
    }

    public ItemStackBuilder addLore(String... lines) {
        editor.addLore(lines);
        return this;
    }

    public ItemStackBuilder setCustomModelData(int data) {
        editor.setCustomModelData(data);
        return this;
    }

    public ItemStackBuilder setClickAction(ClickAction action) {
        setNbtData(ItemStacks.CLICK_ACTION_NAMESPACE, CustomPersistentDataType.CLICK_ACTION, action);
        return this;
    }

    public ItemStackBuilder setEatAction(EatAction action) {
        setNbtData(ItemStacks.EAT_ACTION_NAMESPACE, CustomPersistentDataType.EAT_ACTION, action);
        return this;
    }

    public ItemStackBuilder setFeedAmount(int feed) {
        setNbtData(ItemStacks.FEED_AMOUNT_NAMESPACE, PersistentDataType.INTEGER, feed);
        return this;
    }

    public ItemStackBuilder setAmount(int amount) {
        editor.getItem().setAmount(amount);
        return this;
    }

    public ItemStackBuilder addAttributeModifier(Attribute attribute, AttributeModifier modifier) {
        editor.addAttributeModifier(attribute, modifier);
        return this;
    }

    public ItemStackBuilder addAttributeModifier(Attribute attribute, double amount, AttributeModifier.Operation operation) {
        editor.addAttributeModifier(attribute, amount, operation);
        return this;
    }

    public ItemStackBuilder addAttributeModifier(Attribute attribute, double amount, AttributeModifier.Operation operation, EquipmentSlot slot) {
        editor.addAttributeModifier(attribute, amount, operation, slot);
        return this;
    }

    public ItemStack toItemStack() {
        setNbtData(ItemStacks.IS_CUSTOM_ITEM_NAMESPACE, PersistentDataType.INTEGER, 1);
        return editor.finish();
    }
}
