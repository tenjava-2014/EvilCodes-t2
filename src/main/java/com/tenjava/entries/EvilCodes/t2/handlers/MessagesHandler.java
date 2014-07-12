package com.tenjava.entries.EvilCodes.t2.handlers;

public class MessagesHandler {

    /**
     * Get a message from messages.yml file with the prefix from config.yml file
     * @param msg The message location (or so. IDK)
     * @return Message from file with prefix
     */
    public static String convert(final String msg) {
        return FilesHandler.getMessages().getString(FilesHandler.getConfig().getString("prefix") + msg).replace("&", "§");
    }
}
