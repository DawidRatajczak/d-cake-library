package pl.crazydev.dcakelibrary.listener.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.crazydev.dcakelibrary.DCakeLibrary;
import pl.crazydev.dcakelibrary.listener.EventListener;
import pl.crazydev.dcakelibrary.listener.RegisterListener;
import pl.crazydev.dcakelibrary.message.Msg;
import pl.crazydev.dcakelibrary.plugin.PluginComponent;

@RegisterListener
@PluginComponent
public class CakePlayerJoinListener extends EventListener<DCakeLibrary> {
    public CakePlayerJoinListener(DCakeLibrary plugin) {
        super(plugin);
    }

    @EventHandler
    private void onEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        plugin.getRecipesManager().unlockRecipesForPlayer(player);

        if(plugin.getConfig().getBoolean("resource-pack")) {
            player.setResourcePack(DCakeLibrary.RESOURCE_PACK_LINK);
        }

        Msg.sendMessage(player, "§aThe server uses RDCakeLib which was created by §eDawid Ratajczak");
    }
}
