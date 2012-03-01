package com.precipicegames.zeryl.hidenseek;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 *
 * @author Zeryl
 */
public class HideNSeekPlayerListener implements Listener {
    
    private final HideNSeek plugin;
    
    public HideNSeekPlayerListener(HideNSeek instance) {
        plugin = instance;
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        
        plugin.removePlayer(player);
    }
}