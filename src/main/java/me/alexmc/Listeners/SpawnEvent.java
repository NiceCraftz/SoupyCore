package me.alexmc.Listeners;

import me.alexmc.SoupyCore;
import me.alexmc.Utils.Methods;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class SpawnEvent implements Listener {

    private final Methods methods = SoupyCore.getInstance().getMethods();

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        if(methods.boolConfig("customkit")) {
            methods.defaultKit(e.getPlayer());
        }

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.getPlayer().setFoodLevel(20);
        e.getPlayer().setHealth(e.getPlayer().getMaxHealth());
        if(methods.boolConfig("customkit")) {
            methods.defaultKit(e.getPlayer());
        }
    }
}
