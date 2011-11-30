package com.precipicegames.zeryl.hidenseek;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 *
 * @author Zeryl
 */
public class HideNSeekPlayerListener extends PlayerListener {
    
    private final HideNSeek plugin;
    
    public HideNSeekPlayerListener(HideNSeek instance) {
        plugin = instance;
    }
    
    @Override
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        
        plugin.removePlayer(player);
    }
}