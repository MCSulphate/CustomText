package me.sulphate.customtext;

import me.sulphate.customtext.managers.ConfigsManager;
import me.sulphate.customtext.utils.GeneralUtils;
import me.sulphate.customtext.utils.Messages;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main class for the plugin. Handles enabling/disabling.
 */
public class CustomText extends JavaPlugin {

    private static CustomText plugin;
    private ConfigsManager configsManager;

    /**
     * Method to be run when the plugin should be enabled, to set it up.
     */
    @Override
    public void onEnable() {
        plugin = this;

        configsManager = new ConfigsManager();
        Messages.reloadMessages(configsManager);

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

}
