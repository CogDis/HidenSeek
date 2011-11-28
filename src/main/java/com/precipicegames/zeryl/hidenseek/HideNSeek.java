package com.precipicegames.zeryl.hidenseek;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.ChatColor;

/**
 *
 * @author Zeryl
 */
public class HideNSeek extends JavaPlugin {
    public boolean state = false;
    
    private final HideNSeekEntityListener entityListener = new HideNSeekEntityListener(this);
    
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
                state = !state;
                sender.sendMessage("Hide n Seek is now " + ChatColor.YELLOW + ((state == true) ? "On" : "Off"));
            } else {
                sender.sendMessage("Hide n Seek is " + ChatColor.YELLOW + ((state == true) ? "On" : "Off"));
                return false;
            }
        }
        return true;
    }
}
