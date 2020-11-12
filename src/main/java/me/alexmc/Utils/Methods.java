package me.alexmc.Utils;

import me.alexmc.SoupyCore;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Methods {

    private final SoupyCore instance = SoupyCore.getInstance();

    public String cConfig(String path) {
        return ChatColor.translateAlternateColorCodes('&', instance.getCustomConfig().getString(path));
    }

    public String strConfig(String path) {
        return instance.getCustomConfig().getString(path);
    }

    public Boolean boolConfig(String path) {
        return instance.getCustomConfig().getBoolean(path);
    }

    public Integer intConfig(String path) {
        return instance.getCustomConfig().getInt(path);
    }

    public Double doubleConfig(String path) {
        return instance.getCustomConfig().getDouble(path);
    }

    public String cMsg(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public List<String> listConfig(String path) {
        return instance.getCustomConfig().getStringList(path);
    }

    public String repl(String s, Player victim, Player killer) {
        return s.replaceAll("%victim%", victim.getName()).replaceAll("%killer%", killer.getName());
    }


    public void defaultKit(Player p) {
        p.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
        p.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
        p.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
        p.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta swordMeta = sword.getItemMeta();
        swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
        sword.setItemMeta(swordMeta);
        p.getInventory().addItem(sword);
        p.getInventory().addItem(new ItemStack(Material.BOW));
        p.getInventory().addItem(new ItemStack(Material.ARROW, 16));
    }

}
