package com.tenjava.entries.EvilCodes.t2.handlers;

import com.tenjava.entries.EvilCodes.t2.TenJava;
import org.bukkit.Bukkit;

public class LogHandler {

    public static void log(final String msg) {
        Bukkit.getLogger().info(TenJava.prefix + msg);
    }

    public static void err(final String msg) {
        Bukkit.getLogger().severe(TenJava.prefix + msg);
    }

    public static void stackErr(final Exception ex) {
        if (TenJava.debug)
            ex.printStackTrace();
        else
            err(TenJava.prefix + "An error occurred!");
    }
}
