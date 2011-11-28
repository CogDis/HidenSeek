package com.precipicegames.zeryl.hidenseek;

import java.util.HashSet;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 *
 * @author Zeryl
 */
public class HideNSeekPlayerListener extends PlayerListener {
    
    private final HideNSeek plugin;
    
    private HashSet<String> commands;
        
    public HideNSeekPlayerListener(HideNSeek instance) {
        plugin = instance;
    }
    
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        
        plugin.removePlayer(player);
    }
}