package com.precipicegames.zeryl.hidenseek;

import java.util.HashSet;
import java.util.Iterator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 *
 * @author Zeryl
 */
public class HideNSeek extends JavaPlugin {
    public boolean state = false;
    
    private final HideNSeekEntityListener entityListener = new HideNSeekEntityListener(this);
    
    private HashSet<Player> players;
    
    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();
        PluginDescriptionFile pdf = this.getDescription();
        pm.registerEvent(Event.Type.ENTITY_DAMAGE, entityListener, Priority.Monitor, this);
        System.out.println(pdf.getName() + " is now enabled.");
    }
    
    public void onDisable() {
        PluginDescriptionFile pdf = this.getDescription();
        System.out.println(pdf.getName() + " is now disabled.");
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if(cmd.getName().equalsIgnoreCase("hns")) {
            if(args.length > 0 && args[0].equalsIgnoreCase("toggle") && sender.hasPermission("precipice.hidenseek.toggle")) {
                if(sender instanceof Player) {
                    if(this.players.contains(sender)) {
                        this.players.remove((Player) sender);
                        sender.sendMessage("Hide n Seek is now " + ChatColor.YELLOW + "Off");
                        this.sendToPlayers(sender.getName() + " is no longer playing Hide n Seek!", ChatColor.RED);
                    }
                    else {
                        this.players.add((Player) sender);
                        sender.sendMessage("Hide n Seek is now " + ChatColor.YELLOW + "On");
                        this.sendToPlayers(sender.getName() + " is now playing Hide n Seek!", ChatColor.RED);
                    }
                }
            }
            else {
                if(this.players.contains(sender))
                    sender.sendMessage("You are currently participating in Hide n Seek!");
                else
                    sender.sendMessage("You are currently " + ChatColor.RED + "not" + ChatColor.WHITE + " participating in Hide n Seek!");
            }
        }
        return true;
    }
    
    public boolean isPlaying(Player player) {
        if(this.players.contains(player))
            return true;
        else
            return false;
    }
    
    public boolean isRunning() {
        return this.players.isEmpty();
    }
    
    public void sendToPlayers(String message, ChatColor color) {
        Iterator it = this.players.iterator();
        while(it.hasNext()) {
            Player player = (Player) it.next();
            player.sendMessage(color + message);
        }
    }
}
