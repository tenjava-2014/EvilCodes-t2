package com.tenjava.entries.EvilCodes.t2.listeners;

import com.tenjava.entries.EvilCodes.t2.handlers.DatabaseHandler;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent e) {
        final Player player = e.getPlayer();
        if (!DatabaseHandler.userExists(player)) {
            DatabaseHandler.insert(player);
        }
        player.setExp(DatabaseHandler.getEnergy(player));
        player.setGameMode(GameMode.SURVIVAL);
    }

    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent e) {
        final Player player = e.getPlayer();
        DatabaseHandler.getConnection().execute("UPDATE `" + DatabaseHandler.getPrefix() + "player` SET `energy` = '" + player.getExp() + "' WHERE `uuid` = '" + player.getUniqueId().toString() + "';");
    }
}
