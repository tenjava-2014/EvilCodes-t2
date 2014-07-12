package com.tenjava.entries.EvilCodes.t2.listeners;

import com.tenjava.entries.EvilCodes.t2.handlers.DatabaseHandler;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent e) {
        final Player player = e.getPlayer();
        if (!DatabaseHandler.userExists(player)) {
            DatabaseHandler.insert(player);
        }
        player.setExp(DatabaseHandler.getEnergy(player));
        player.setGameMode(GameMode.SURVIVAL);
        //TODO Update lastlogin
    }

    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent e) {
        final Player player = e.getPlayer();
        DatabaseHandler.getConnection().execute("UPDATE `" + DatabaseHandler.getPrefix() + "player` SET `energy` = '" + player.getExp() + "' WHERE `uuid` = '" + player.getUniqueId().toString() + "';");
    }

    @EventHandler
    public void onEntityDeath(final EntityDeathEvent e) {
        if (e.getEntityType() != EntityType.PLAYER) {
            if (e.getEntity().getKiller() != null) {
                if (e.getEntity().getKiller().getType() == EntityType.PLAYER) {
                    final Player killer = e.getEntity().getKiller();
                    final LivingEntity entity = e.getEntity();
                    if (entity.getType() == EntityType.ZOMBIE || entity.getType() == EntityType.CREEPER || entity.getType() == EntityType.SKELETON || entity.getType() == EntityType.SPIDER || entity.getType() == EntityType.ENDERMAN) {
                        DatabaseHandler.increaseValue("mobkills", killer);
                    }
                    killer.setLevel(DatabaseHandler.getValue("mobkills", killer));
                }
            }
        }
    }

    @EventHandler
    public void onExpChange(final PlayerExpChangeEvent e) {
        e.setAmount(0);
        e.getPlayer().setExp(DatabaseHandler.getEnergy(e.getPlayer()));
    }

    @EventHandler
    public void onPlayerDeath(final PlayerDeathEvent e) {
        final Player player = e.getEntity();
        DatabaseHandler.increaseValue("deaths", player);
    }

    @EventHandler
    public void onPlayerInteract(final PlayerInteractEvent e) {
        //TODO Chests
    }

    @EventHandler
    public void onPlayerLevelUp(final PlayerLevelChangeEvent e) {
        e.getPlayer().setLevel(DatabaseHandler.getValue("mobkills", e.getPlayer()));
    }

    @EventHandler
    public void onCreatureSpawn(final CreatureSpawnEvent e) {
        final Entity entity = e.getEntity();
        if (entity.getType() == EntityType.PIG || entity.getType() == EntityType.COW || entity.getType() == EntityType.CHICKEN || entity.getType() == EntityType.SHEEP) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onHungerLevelChange(final FoodLevelChangeEvent e) {
        e.setCancelled(true);
        e.setFoodLevel(20);
    }
}
