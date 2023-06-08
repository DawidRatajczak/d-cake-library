package pl.crazydev.dcakelibrary.data.persistence.nbt;

import org.bukkit.block.Block;
import org.bukkit.block.TileState;
import org.bukkit.persistence.PersistentDataContainer;

public class BlockNbtContainer extends NbtContainerBase {
    private final Block block;

    BlockNbtContainer(Block block) {
        if (!(block.getState() instanceof TileState)) {
            throw new IllegalArgumentException("Block state needs to be tile state");
        }
        this.block = block;
    }

    @Override
    public void save() {
        block.getState().update();
    }

    @Override
    protected PersistentDataContainer getContainer() {
        return ((TileState) block).getPersistentDataContainer();
    }
}
