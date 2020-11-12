package me.alexmc.Listeners;

import me.alexmc.SoupyCore;
import me.alexmc.Utils.Methods;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodEvent implements Listener {
    private final Methods methods = SoupyCore.getInstance().getMethods();
    @EventHandler
    public void foodChange(FoodLevelChangeEvent e) {
        if(methods.boolConfig("antifoodlevelchange")) {
            e.setCancelled(true);
        }
    }
}
