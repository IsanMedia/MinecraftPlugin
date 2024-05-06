package com.yourdomain;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.entity.Player;

public class XpDamageResist extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Ensure config file is created or loaded
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("XpDamageResist enabled!");
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            int xpLevel = player.getLevel();
            double modifier = getConfig().getDouble("damage-modifiers.level-" + xpLevel, 1.0);
            event.setDamage(event.getDamage() * modifier);
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("XpDamageResist disabled.");
    }
}