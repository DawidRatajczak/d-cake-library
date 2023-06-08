package pl.crazydev.dcakelibrary.data.persistence.nbt;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Optional;

public abstract class NbtContainerBase implements NbtContainer {
    @Override
    public <T, Z> Optional<Z> get(NamespacedKey key, PersistentDataType<T, Z> type) {
        return getContainer().has(key, type) ? Optional.ofNullable(getContainer().get(key, type)) : Optional.empty();
    }

    @Override
    public <T, Z> Optional<Z> get(String namespace, PersistentDataType<T, Z> type) {
        return get(NbtContainer.createKey(namespace), type);
    }

    @Override
    public <T, Z> boolean has(NamespacedKey key, PersistentDataType<T, Z> type) {
        return get(key, type).isPresent();
    }

    @Override
    public <T, Z> boolean has(String namespace, PersistentDataType<T, Z> type) {
        return get(namespace, type).isPresent();
    }

    @Override
    public <T, Z> NbtContainer set(NamespacedKey key, PersistentDataType<T, Z> type, Z value) {
        getContainer().set(key, type, value);
        return this;
    }

    @Override
    public <T, Z> NbtContainer set(String namespace, PersistentDataType<T, Z> type, Z value) {
        getContainer().set(NbtContainer.createKey(namespace), type, value);
        return this;
    }

    @Override
    public NbtContainer remove(NamespacedKey key) {
        getContainer().remove(key);
        return this;
    }

    @Override
    public NbtContainer remove(String namespace) {
        remove(NbtContainer.createKey(namespace));
        return this;
    }

    protected abstract PersistentDataContainer getContainer();
}
