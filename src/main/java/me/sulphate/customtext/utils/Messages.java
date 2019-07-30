package me.sulphate.customtext.utils;

import me.sulphate.customtext.managers.ConfigsManager;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * Contains static Strings to be used throughout the plugin.
 */
public class Messages {

    // General Plugin Strings
    public static String PREFIX;

    // Startup/Shutdown Messages
    public static String LOADED_COMMANDS;
    public static String PLUGIN_ENABLED;
    public static String PLUGIN_DISABLED;

    // General Error Messages
    public static String CONFIG_FAILED_TO_SAVE;

    // Command-Related Messages
    public static String NOT_ENOUGH_ARGS;
    public static String NO_PERMISSION;
    public static String COMMAND_ADDED;
    public static String COMMAND_REMOVED;

    /**
     * Reloads all messages from the messages.yml config.
     *
     * @param configsManager The instance of the configs manager.
     * @see   ConfigsManager
     */
    public static void reloadMessages(ConfigsManager configsManager) {
        FileConfiguration config = configsManager.getConfig("messages.yml");

        PREFIX = getAndColourise("prefix", config);

        LOADED_COMMANDS = PREFIX + getAndColourise("loaded-commands", config);
        PLUGIN_ENABLED = PREFIX + getAndColourise("plugin-enabled", config);
        PLUGIN_DISABLED = PREFIX + getAndColourise("plugin-disabled", config);

        CONFIG_FAILED_TO_SAVE = PREFIX + getAndColourise("config-failed-to-save", config);

        NOT_ENOUGH_ARGS = getAndColourise("not-enough-args", config);
        NO_PERMISSION = getAndColourise("no-permission", config);
        COMMAND_ADDED = getAndColourise("command-added", config);
        COMMAND_REMOVED = getAndColourise("command-removed", config);
    }

    /**
     * Returns a colourised String from the messages config.
     *
     * @param key    The name of the message.
     * @param config The FileConfiguration representing messages.yml.
     * @return       The colourised retrieved String.
     */
    private static String getAndColourise(String key, FileConfiguration config) {
        return GeneralUtils.colourise(config.getString(key));
    }

}
