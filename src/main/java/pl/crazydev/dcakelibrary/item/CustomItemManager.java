package pl.crazydev.dcakelibrary.item;

import org.bukkit.inventory.ItemStack;

import java.util.*;

public class CustomItemManager {
    private Map<String, ItemStack> customItems = new HashMap<>();

    public void addCustomItem(ItemStack item, String id) {
        if(customItems.containsKey(id)) {
            return;
        }

        customItems.put(id, item);
    }

    public Set<Map.Entry<String, ItemStack>> getCustomItemsEntrySet() {
        return customItems.entrySet();
    }

    public Set<String> getIds() {
        return customItems.keySet();
    }

    public Collection<ItemStack> getCustomItems() {
        return customItems.values();
    }

    public Optional<ItemStack> getCustomItem(String id) {
        List<String> ids = getIds().stream()
                .filter(itemId -> itemId.equals(id))
                .toList();

        if (ids.size() < 1) {
            return Optional.empty();
        }

        return Optional.ofNullable(customItems.get(id));
    }
}
