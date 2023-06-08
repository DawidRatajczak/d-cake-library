package pl.crazydev.dcakelibrary.data.persistence.nbt;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import pl.crazydev.dcakelibrary.item.action.ClickAction;
import pl.crazydev.dcakelibrary.item.action.EatAction;

public class CustomPersistentDataType {
    public static final PersistentDataType<byte[], ClickAction> CLICK_ACTION = new PersistentDataTypeBuilder(false, ClickAction.class);
    public static final PersistentDataType<byte[], ItemStack> ITEM_STACK = new PersistentDataTypeBuilder(true, ItemStack.class);
    public static final PersistentDataType<byte[], Location> LOCATION = new PersistentDataTypeBuilder(true, Location.class);
    public static final PersistentDataType<byte[], ItemStack[]> ITEM_STACK_ARRAY = new PersistentDataTypeBuilder(true, ItemStack[].class);
    public static final PersistentDataType<byte[], EatAction> EAT_ACTION = new PersistentDataTypeBuilder(false, EatAction.class);
}
