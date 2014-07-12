package com.tenjava.entries.EvilCodes.t2.listeners;

import com.tenjava.entries.EvilCodes.t2.handlers.DatabaseHandler;
import com.tenjava.entries.EvilCodes.t2.handlers.FilesHandler;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;

import java.util.ArrayList;

public class PlayerListener implements Listener {

    public static final ArrayList<Player> minusStr2 = new ArrayList<Player>();
    public static final ArrayList<Player> minusStr1 = new ArrayList<Player>();

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent e) {
        final Player player = e.getPlayer();
        if (!DatabaseHandler.userExists(player)) {
            DatabaseHandler.insert(player);
            player.setExp(0.99f);
        }
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
                        killer.setExp(killer.getExp() + 0.05f);
                    }
                    killer.setLevel(DatabaseHandler.getValue("mobkills", killer));
                }
            }
        }
        e.setDroppedExp(0);
    }

    @EventHandler
    public void onExpChange(final PlayerExpChangeEvent e) {
        e.setAmount(0);
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

    @EventHandler
    public void onEntityDamageByEnity(final EntityDamageByEntityEvent e) {
        if (e.getEntity().getType() == EntityType.PLAYER) {
            final Player player = (Player) e.getEntity();
            if (e.getDamager().getType() == EntityType.PLAYER) {
                if (!FilesHandler.getConfig().getBoolean("pvp"))
                    e.setCancelled(true);
            }
        } else {
            if (e.getDamager().getType() == EntityType.PLAYER) {
                final Player damager = (Player) e.getDamager();

            }
        }
    }

    @EventHandler
    public void onPlayerRespawn(final PlayerRespawnEvent e) {
        e.getPlayer().setExp(0.99f);
    }
}
