package pl.crazydev.dcakelibrary.menu;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import pl.crazydev.dcakelibrary.DCakeLibrary;

public class CustomItemMenu extends PaginatedInventoryMenu<DCakeLibrary> {
    public CustomItemMenu(DCakeLibrary plugin, Player menuOwner) {
        super(plugin, menuOwner);
    }

    @Override
    public void handle(InventoryClickEvent clickEvent) {

    }

    @Override
    public String getName() {
        return "przedmioty";
    }

    @Override
    public int getSize() {
        return 54;
    }

    @Override
    public void setContent() {
        int index = 0;
        int page = 0;

        for(ItemStack item : DCakeLibrary.getPlugin().getCustomItemManager().getCustomItems()) {
            if(item == null) {
                continue;
            }

            setSlot(item, index, page);

            if(index >= 53) {
                index = 0;
                page++;
                continue;
            }

            index++;
        }
    }

    @Override
    public int getPageSize() {
        int pageSize = 1;
        int customItemsAmount = DCakeLibrary.getPlugin().getCustomItemManager().getCustomItems().size();

        while (customItemsAmount > 0) {
            customItemsAmount -= 54;
            if(customItemsAmount > 0) {
                pageSize++;
            }
        }

        return pageSize;
    }
}
