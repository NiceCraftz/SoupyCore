package me.alexmc;

import me.alexmc.Commands.MainCommand;
import me.alexmc.Listeners.FoodEvent;
import me.alexmc.Listeners.KillEvent;
import me.alexmc.Listeners.SoupEvent;
import me.alexmc.Listeners.SpawnEvent;
import me.alexmc.Utils.Methods;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.EnumSet;

public final class SoupyCore extends JavaPlugin {

    public String prefix = "§7[§6SoupyCore§7] ";
    public static Methods methods;
    public static SoupyCore instance;
    private FileConfiguration customConfig;
    private final File customConfigFile = new File(getDataFolder(), "config.yml");
    public EnumSet<Material> soups = EnumSet.noneOf(Material.class);

    public static SoupyCore getInstance() {
        return instance;
    }

    public Methods getMethods() {
        return methods;
    }

    @Override
    public void onEnable() {
        instance = this;
        createCustomConfig();
        methods = new Methods();
        for(Material material : Material.values()) {
            if(material.name().toLowerCase().contains("stew") || material.name().toLowerCase().contains("soup")) {
                soups.add(material);
                getLogger().warning("Added " + material.toString() + " to the Material List");
            }
        }
        getCommand("soupy").setExecutor(new MainCommand());
        getServer().getPluginManager().registerEvents(new SoupEvent(), this);
        getServer().getPluginManager().registerEvents(new KillEvent(), this);
        getServer().getPluginManager().registerEvents(new SpawnEvent(), this);
        getServer().getPluginManager().registerEvents(new FoodEvent(), this);

    }

    @Override
    public void onDisable() {
        try {
            getCustomConfig().save(customConfigFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public FileConfiguration getCustomConfig() {
        return customConfig;
    }

    private void createCustomConfig() {
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }

        customConfig = new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void configReload() throws IOException, InvalidConfigurationException {
        customConfig.load(customConfigFile);
    }
}
