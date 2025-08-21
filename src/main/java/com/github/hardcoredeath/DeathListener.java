package com.github.hardcoredeath;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.entity.Player;

public class DeathListener implements Listener {

    private final HardcoreDeath plugin;

    public DeathListener(HardcoreDeath plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        String worldName = player.getWorld().getName();
        if (!plugin.isWorldEnabled(worldName)) return;

        // Prevent drops
        event.getDrops().clear();

        // Clear inventory (just in case)
        try {
            player.getInventory().clear();
            player.getEquipment().setArmorContents(null);
        } catch (Exception ignored) {
        }
    }
}
