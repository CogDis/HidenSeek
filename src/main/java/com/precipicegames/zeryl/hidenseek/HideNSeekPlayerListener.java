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
    
    public void onPlayerCommandPreProcess(PlayerChatEvent event) {
        commands.add("/tp");
        commands.add("/tpo");
        commands.add("/ascend");
        commands.add("/ceil");
        commands.add("/up");
        commands.add("/descend");
        
        String[] args = event.getEventName().split(" ");
        
        if(event.getPlayer().hasPermission("precipice.hidenseek.overridecommands") || !plugin.isPlaying(event.getPlayer()))
            return;
        if(args.length > 0 && commands.contains((String) args[0])) {
            plugin.sendToPlayers(event.getPlayer().getDisplayName() + " has attempted to cheat by using " + args[0].toString(), ChatColor.RED);
            event.setCancelled(true);
            return;
        }
    }
}
