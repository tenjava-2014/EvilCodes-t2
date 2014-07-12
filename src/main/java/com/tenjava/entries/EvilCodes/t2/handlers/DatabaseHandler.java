package com.tenjava.entries.EvilCodes.t2.handlers;

import com.tenjava.entries.EvilCodes.t2.utils.DBCore;
import com.tenjava.entries.EvilCodes.t2.utils.MySQLCore;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHandler {

    private static DBCore connection;
    private static String prefix;

    /*
    me_player: id, name, uuid, kills, deaths, energy, firstlogin, lastlogin, mobkills
     */

    public static void setup() {
        DatabaseHandler.connection = new MySQLCore(FilesHandler.getConfig().getString("database.host"), FilesHandler.getConfig().getString("database.database"),
                FilesHandler.getConfig().getString("database.username"), FilesHandler.getConfig().getString("database.password"));
        DatabaseHandler.prefix = FilesHandler.getConfig().getString("database.prefix");
    }

    public static boolean userExists(final Player player) {
        final String ifquery = "SELECT * FROM `" + prefix + "player" + "` WHERE `uuid` = '" + player.getUniqueId().toString() + "';";
        return MySQLCore.mysqlExists(ifquery);
    }

    public static int getValue(final String row, final Player player) {
        final String query = "SELECT * FROM `" + prefix + "player" + "` WHERE `uuid` = '" + player.getUniqueId().toString() + "';";
        final ResultSet resultSet = connection.select(query);
        int value = 0;
        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    value = resultSet.getInt(row);
                }
            } catch (final SQLException ex) {
                LogHandler.err("Error while trying to get value from database for player " + player.getName());
                LogHandler.stackErr(ex);
            }
        } else {
            LogHandler.err("Cannot get " + row + " for " + player.getName() + ": Resultset is null!");
        }
        return value;
    }
}
