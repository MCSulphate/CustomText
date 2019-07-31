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
    public static String PAPI_HOOKED;

    // General Error Messages
    public static String CONFIG_FAILED_TO_SAVE;
    public static String COMMAND_IN_OTHER_PLUGIN;

    // Command-Related Messages
    public static String NOT_ENOUGH_ARGS;
    public static String NO_PERMISSION;
    public static String COMMAND_ADDED;
    public static String COMMAND_REMOVED;
    public static String CONFIGS_RELOADED;
    public static String LIST_MESSAGE;
    public static String LIST_MESSAGE_FORMAT;
    public static String COMMAND_DOES_NOT_EXIST;
    public static String COMMAND_ALREADY_EXISTS;
    public static String INVALID_COMMAND;

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
        PAPI_HOOKED = PREFIX + getAndColourise("papi-hooked", config);

        CONFIG_FAILED_TO_SAVE = PREFIX + getAndColourise("config-failed-to-save", config);
        COMMAND_IN_OTHER_PLUGIN = PREFIX + getAndColourise("command-in-other-plugin", config);

        NOT_ENOUGH_ARGS = PREFIX + getAndColourise("not-enough-arguments", config);
        NO_PERMISSION = PREFIX + getAndColourise("no-permission", config);
        COMMAND_ADDED = PREFIX + getAndColourise("command-added", config);
        COMMAND_REMOVED = PREFIX + getAndColourise("command-removed", config);
        CONFIGS_RELOADED = PREFIX + getAndColourise("configs-reloaded", config);
        LIST_MESSAGE = PREFIX + getAndColourise("list-message", config);
        LIST_MESSAGE_FORMAT = getAndColourise("list-message-format", config);
        COMMAND_DOES_NOT_EXIST = PREFIX + getAndColourise("command-does-not-exist", config);
        COMMAND_ALREADY_EXISTS = PREFIX + getAndColourise("command-already-exists", config);
        INVALID_COMMAND = PREFIX + getAndColourise("invalid-command", config);
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
