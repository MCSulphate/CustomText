package me.sulphate.customtext.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

/**
 * Contains general utility functions for the plugin.
 */
public class GeneralUtils {

    /**
     * Colourises a string using '&amp;' as the colour-code token.
     *
     * @param toColourise The String to colourise.
     * @return            The colourised String.
     */
    public static String colourise(String toColourise) {
        return ChatColor.translateAlternateColorCodes('&', toColourise);
    }

    /**
     * Sends a message to the server console.
     *
     * @param message The message to send.
     */
    public static void sendConsoleMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(message);
    }

}
