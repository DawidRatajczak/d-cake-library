package pl.crazydev.dcakelibrary.data.persistence.nbt;

import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import pl.crazydev.dcakelibrary.DCakeLibrary;

import java.util.Optional;

public interface NbtContainer {

    static NbtContainer get(ItemStack item) {
        return new ItemStackNbtContainer(item);
    }

    static NbtContainer get(Block block) {
        return new BlockNbtContainer(block);
    }

    static NbtContainer get(Entity entity) {
        return new EntityNbtContainer(entity);
    }

    static NamespacedKey createKey(String namespace) {
        return new NamespacedKey(DCakeLibrary.getPlugin(), namespace);
    }

    <T, Z> Optional<Z> get(NamespacedKey key, PersistentDataType<T, Z> type);
    <T, Z> Optional<Z> get(String namespace, PersistentDataType<T, Z> type);
    <T, Z> boolean has(NamespacedKey key, PersistentDataType<T, Z> type);
    <T, Z> boolean has(String namespace, PersistentDataType<T, Z> type);
    <T, Z> NbtContainer set(NamespacedKey key, PersistentDataType<T, Z> type, Z value);
    <T, Z> NbtContainer set(String namespace, PersistentDataType<T, Z> type, Z value);
    NbtContainer remove(NamespacedKey key);
    NbtContainer remove(String namespace);
    void save();
}


