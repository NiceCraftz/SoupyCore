package me.alexmc.Listeners;

import me.alexmc.SoupyCore;
import me.alexmc.Utils.Methods;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class KillEvent implements Listener {

    private final Methods methods = SoupyCore.getInstance().getMethods();
    private final List<String> list = methods.listConfig("consolerewards.commands");

    @EventHandler
    public void killEvent(PlayerDeathEvent e) {

        Player killer = e.getEntity().getKiller();
        Player victim = e.getEntity();
        victim.getInventory().clear();

        if (killer != null && methods.boolConfig("customdeath.enabled")) {
            e.setDeathMessage(methods.cConfig("customdeath.deathmsg")
                    .replaceAll("%victim%", victim.getName())
                    .replaceAll("%killer%", killer.getName()));

            if (killer == victim) {
                e.setDeathMessage(methods.cConfig("customdeath.selfkill")
                        .replaceAll("%victim%", victim.getName())
                        .replaceAll("%killer%", killer.getName()));
            }

            if (methods.boolConfig("killheal.enabled")) {
                if (methods.boolConfig("killheal.full")) {
                    if (!(killer == victim)) {
                        killer.setHealth(killer.getMaxHealth());
                    }
                } else if (!(killer == victim)) {
                    killer.setHealth(Math.min(killer.getMaxHealth(), killer.getHealth() + methods.doubleConfig("hearts")));
                }
            }
        }

        if (methods.boolConfig("reward.enabled")) {
            killer.getInventory().addItem(
                    new ItemStack(Material.valueOf(
                            methods.strConfig("reward.item").toUpperCase()),
                            methods.intConfig("reward.quantity")));
        }

        if (methods.boolConfig("consolereward.enabled")) {
            for (String string : list) {

                list.replaceAll(s -> )

                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s);

            }
        }
    }
}
