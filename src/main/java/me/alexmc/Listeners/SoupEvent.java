package me.alexmc.Listeners;

import me.alexmc.SoupyCore;
import me.alexmc.Utils.Methods;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class SoupEvent implements Listener {

    private final Methods methods = SoupyCore.getInstance().getMethods();
    private final SoupyCore instance = SoupyCore.getInstance();

    @EventHandler
    public void onSoup(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        ItemStack item = e.getPlayer().getInventory().getItemInHand();
        Action action = e.getAction();
        if (methods.boolConfig("soupheal.enabled")) {
            if ((action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) && instance.soups.contains(item.getType())) {
                if(methods.boolConfig("full")) {
                    player.setHealth(player.getMaxHealth());
                }else {
                    player.setHealth(Math.min(player.getMaxHealth(), player.getHealth() + methods.doubleConfig("soupheal.hearts")));
                }
            }
        }
    }
}
