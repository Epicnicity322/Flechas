package com.epicnicity322.flechas;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Flechas extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onArrow(ProjectileHitEvent event) {
        if (!event.getEntityType().name().contains("ARROW")) return;

        Block block = event.getHitBlock();

        if (block == null || !block.getType().name().contains("GLASS")) return;

        if (event.getEntity().getShooter() instanceof Player player) {
            BlockBreakEvent bbe = new BlockBreakEvent(block, player);
            getServer().getPluginManager().callEvent(bbe);
            if (bbe.isCancelled()) return;
        }

        block.breakNaturally();
    }
}
