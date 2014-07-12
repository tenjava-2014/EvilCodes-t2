package com.tenjava.entries.EvilCodes.t2;

import com.tenjava.entries.EvilCodes.t2.handlers.FilesHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class TenJava extends JavaPlugin {

    public static TenJava instance;
    public static String prefix = "[MineEscape] ";
    public static final boolean debug = true;

    public void onEnable() {
        instance = this;
        FilesHandler.setup();

    }

    public void onDisable() {

    }
}
