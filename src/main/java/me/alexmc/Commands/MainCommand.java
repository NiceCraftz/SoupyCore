package me.alexmc.Commands;

import me.alexmc.SoupyCore;
import me.alexmc.Utils.Methods;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

import java.io.IOException;

public class MainCommand implements CommandExecutor {

    private final Methods methods = SoupyCore.getInstance().getMethods();
    private final SoupyCore instance = SoupyCore.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("soupy.admin") && sender instanceof Player) {
            if(args.length == 0) {
                sender.sendMessage(methods.cMsg(
                        "&7&m--------------------------\n" +
                        "&6&l• &7Running &6&lSoupyCore v2 &7by NiceCraftz" +
                        "\n" +
                        "&6&l> &e/soupy reload\n" +
                        "&7&m--------------------------"));
            }else if(args[0].equalsIgnoreCase("reload")) {
                try {
                    instance.configReload();
                } catch (IOException | InvalidConfigurationException e) {
                    e.printStackTrace();
                }
                sender.sendMessage(instance.prefix + methods.cConfig("reload-message"));
            }else {
                sender.sendMessage(methods.cMsg(
                        "&7&m--------------------------\n" +
                        "&6&l• &7Running &6&lSoupyCore v2 &7by NiceCraftz" +
                        "\n" +
                        "&6&l> &e/soupy reload\n" +
                        "&7&m--------------------------"));
            }
        } else {
            sender.sendMessage(methods.cConfig("no-permission"));
        }
        return true;
    }
}
