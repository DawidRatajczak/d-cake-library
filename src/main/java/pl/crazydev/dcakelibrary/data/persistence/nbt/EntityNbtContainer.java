package pl.crazydev.dcakelibrary.data.persistence.nbt;

import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataContainer;

public class EntityNbtContainer extends NbtContainerBase {
    private final Entity entity;

    EntityNbtContainer(Entity entity) {
        this.entity = entity;
    }
    @Override
    public void save() {

    }

    @Override
    protected PersistentDataContainer getContainer() {
        return entity.getPersistentDataContainer();
    }
}
