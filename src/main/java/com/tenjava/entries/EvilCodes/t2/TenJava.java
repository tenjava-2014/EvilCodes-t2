package com.tenjava.entries.EvilCodes.t2;

import com.tenjava.entries.EvilCodes.t2.handlers.DatabaseHandler;
import com.tenjava.entries.EvilCodes.t2.handlers.FilesHandler;
import com.tenjava.entries.EvilCodes.t2.handlers.LogHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class TenJava extends JavaPlugin {

    public static TenJava instance;
    public static String prefix = "[MineEscape] ";
    public static final boolean debug = true;

    public void onEnable() {
        instance = this;
        FilesHandler.setup();
        LogHandler.log("Connecting to database");
        DatabaseHandler.setup();
    }

    public void onDisable() {

    }
}
