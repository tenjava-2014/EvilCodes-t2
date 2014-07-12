package com.tenjava.entries.EvilCodes.t2;

import com.tenjava.entries.EvilCodes.t2.handlers.DatabaseHandler;
import com.tenjava.entries.EvilCodes.t2.handlers.FilesHandler;
import com.tenjava.entries.EvilCodes.t2.handlers.LogHandler;
import com.tenjava.entries.EvilCodes.t2.listeners.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class TenJava extends JavaPlugin {

    //Plugin instance for Scheduler, DataFolder ...
    public static TenJava instance;
    //Plugin prefix in console
    public static String prefix = "[MineEscape] ";
    //Receive debug messages (only for devs)
    public static final boolean debug = false;

    public void onEnable() {
        instance = this;
        FilesHandler.setup();
        LogHandler.log("Connecting to database");
        DatabaseHandler.setup();
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule doDaylighCycle false");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "time set 15000");
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        Timer.start();
    }

    public void onDisable() {
        DatabaseHandler.getConnection().close();
        Bukkit.getScheduler().cancelTask(Timer.task);
    }
}
