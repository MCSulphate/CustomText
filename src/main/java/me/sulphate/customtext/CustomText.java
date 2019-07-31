package me.sulphate.customtext;

import me.sulphate.customtext.commands.CustomTextCommand;
import me.sulphate.customtext.listeners.CommandListener;
import me.sulphate.customtext.managers.CommandsManager;
import me.sulphate.customtext.managers.ConfigsManager;
import me.sulphate.customtext.utils.GeneralUtils;
import me.sulphate.customtext.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main class for the plugin. Handles enabling/disabling.
 */
public class CustomText extends JavaPlugin {

    private static CustomText plugin;
    private boolean papiHooked = false;

    /**
     * Method to be run when the plugin should be enabled, to set it up.
     */
    @Override
    public void onEnable() {
        plugin = this;

        // Set up instances of managers and messages.
        ConfigsManager configsManager = new ConfigsManager();
        Messages.reloadMessages(configsManager);
        CommandsManager commandsManager = new CommandsManager(configsManager);

        // Register the command and the pre-command-process handler.
        getCommand("customtext").setExecutor(new CustomTextCommand(commandsManager, configsManager));
        Bukkit.getPluginManager().registerEvents(new CommandListener(commandsManager), this);

        // Check if PAPI is installed.
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            GeneralUtils.sendConsoleMessage(Messages.PAPI_HOOKED);
            papiHooked = true;
        }

        GeneralUtils.sendConsoleMessage(Messages.PLUGIN_ENABLED);
    }

    /**
     * Method to be run when the plugin is being disabled.
     */
    @Override
    public void onDisable() {
        plugin = null;
    }

    /**
     * Returns the loaded instance of the CustomText plugin.
     *
     * @return The instance of the plugin.
     */
    public static CustomText getPlugin() {
        return plugin;
    }

    /**
     * Returns whether PlaceholderAPI is hooked.
     *
     * @return Whether PlaceholderAPI is hooked.
     */
    public boolean isPAPIHooked() {
        return papiHooked;
    }

}
