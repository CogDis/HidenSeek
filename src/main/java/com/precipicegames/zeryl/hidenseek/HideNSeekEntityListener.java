package com.precipicegames.zeryl.hidenseek;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.ChatColor;

/**
 *
 * @author Zeryl
 */
public class HideNSeekEntityListener extends EntityListener {

    private final HideNSeek plugin;
    
    public HideNSeekEntityListener(HideNSeek instance) {
        plugin = instance;
    }

    @Override
    public void onEntityDamage(EntityDamageEvent event) {
        if(plugin.state != true) {
            return;
        }
        if (event instanceof EntityDamageByEntityEvent) {
            EntityDamageByEntityEvent damage = (EntityDamageByEntityEvent) event;
            if (damage.getDamager() instanceof Arrow) {
                Arrow a = (Arrow) damage.getDamager();
                if (damage.getEntity() instanceof Player && a.getShooter() instanceof Player) {
                    Player shot = (Player) damage.getEntity();
                    Player shooter = (Player) a.getShooter();
                    
                    ItemStack shothelm = shot.getInventory().getHelmet();
                    ItemStack shooterhelm = shooter.getInventory().getHelmet();
                    
                    // When you've been shot by your own team.
                    if(shothelm.getTypeId() == shooterhelm.getTypeId()) {
                        shot.sendMessage(ChatColor.RED + "You've been shot by your own teammate!");
                        shooter.sendMessage(ChatColor.RED + "You shot your own teammate!");
                        return;
                    }
                    
                    // When you shoot someone, but aren't a seeker.
                    if(shooterhelm.getTypeId() == 0) {
                        shooter.sendMessage("You aren't a seeker, so this didn't count.");
                        shot.sendMessage("You've been shot, but they weren't a seeker.");
                        return;
                    }
                    
                    // When you've shot an observer
                    if(shothelm.getTypeId() == Material.GOLD_HELMET.getId()) {
                        shooter.sendMessage("You've shot an observer.");
                        return;
                    }
                    
                    // When you've landed a good shot. This is a test!
                    if(shooterhelm.getTypeId() == Material.DIAMOND_HELMET.getId()) {
                        shot.sendMessage(ChatColor.RED + "You are now on the " + ChatColor.DARK_PURPLE + "seekers" + ChatColor.RED + ".");
                        shooter.sendMessage(ChatColor.RED + "You've sent " + shot.getName() + " to the seekers!");
                        ItemStack item = new ItemStack(Material.DIAMOND_HELMET,1);
                        shot.getInventory().setHelmet(item);
                        shot.updateInventory();
                        return;
                    }
                }
            }
        }
    }
}
