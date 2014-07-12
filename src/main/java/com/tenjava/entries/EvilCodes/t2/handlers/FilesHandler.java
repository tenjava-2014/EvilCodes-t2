package com.tenjava.entries.EvilCodes.t2.handlers;

import com.tenjava.entries.EvilCodes.t2.TenJava;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class FilesHandler {

    private static File configFile;
    private static File messagesFile;

    private static FileConfiguration config;
    private static FileConfiguration messages;

    public static void setup() {
        configFile = new File(TenJava.instance.getDataFolder(), "config.yml");
        messagesFile = new File(TenJava.instance.getDataFolder(), "messages.yml");

        if (!configFile.exists())
            TenJava.instance.saveResource("config.yml", false);
        if (!messagesFile.exists()) {
            TenJava.instance.saveResource("messages.yml", false);
        }

        config = YamlConfiguration.loadConfiguration(configFile);
        messages = YamlConfiguration.loadConfiguration(messagesFile);
    }

    public static FileConfiguration getConfig() {
        return config;
    }

    public static FileConfiguration getMessages() {
        return messages;
    }

}
