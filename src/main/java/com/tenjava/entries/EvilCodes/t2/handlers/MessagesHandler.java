package com.tenjava.entries.EvilCodes.t2.handlers;

public class MessagesHandler {

    public static String convert(final String msg) {
        return FilesHandler.getMessages().getString(msg).replace("&", "§");
    }
}
