package com.github.hardcoredeath;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class HardcoreDeath extends JavaPlugin {

    private List<String> enabledWorlds;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        reloadSettings();
        getServer().getPluginManager().registerEvents(new DeathListener(this), this);
        if (enabledWorlds == null || enabledWorlds.isEmpty()) {
            getLogger().info("HardcoreDeath enabled (no worlds configured)");
        } else {
            getLogger().info("HardcoreDeath enabled — active in worlds: " + String.join(", ", enabledWorlds));
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("HardcoreDeath disabled");
    }

    public void reloadSettings() {
        FileConfiguration cfg = getConfig();
        enabledWorlds = cfg.getStringList("enabled-worlds");
    }

    public boolean isWorldEnabled(String worldName) {
        if (enabledWorlds == null) return false;
        return enabledWorlds.contains(worldName);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("hardcoredeath")) {
            if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                if (!sender.hasPermission("hardcoredeath.reload") && !sender.hasPermission("hardcoredeath.use")) {
                    sender.sendMessage(ChatColor.RED + "You don't have permission to do that.");
                    return true;
                }
                reloadConfig();
                reloadSettings();
                sender.sendMessage(ChatColor.GREEN + "HardcoreDeath config reloaded.");
                return true;
            }

            sender.sendMessage(ChatColor.YELLOW + "HardcoreDeath commands:");
            sender.sendMessage(ChatColor.AQUA + "/hardcoredeath reload" + ChatColor.WHITE + " - Reload the config");
            return true;
        }
        return false;
    }
}
