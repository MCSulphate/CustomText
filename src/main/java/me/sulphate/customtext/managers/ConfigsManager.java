package me.sulphate.customtext.managers;

import me.sulphate.customtext.utils.ConfigUtils;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

/**
 * Manages loading/saving of configs from the file system.
 */
public class ConfigsManager {

    private HashMap<String, FileConfiguration> configs;
    private final String[] CONFIG_NAMES = { "commands.yml", "messages.yml" };

    /**
     * Loads all configs from the file system.
     */
    public ConfigsManager() {
        configs = new HashMap<>();

        for (String configName : CONFIG_NAMES) {
            configs.put(configName, ConfigUtils.loadConfig(configName));
        }
    }

    /**
     * Reloads all configs from the file system.
     */
    public void reloadConfigs() {
        for (String configName : CONFIG_NAMES) {
            configs.remove(configName);
            configs.put(configName, ConfigUtils.loadConfig(configName));
        }
    }

    /**
     * Sets a value in a config, and saves the config.
     *
     * @param configName The name of the config.
     * @param key        The key to save the value to.
     * @param value      The value to be saved to the config.
     */
    public void setConfigValue(String configName, String key, Object value) {
        FileConfiguration config = getConfig(configName);
        config.set(key, value);

        ConfigUtils.saveConfig(config, configName);
    }

    /**
     * Returns a given FileConfiguration.
     *
     * @param configName The name of the config to return.
     * @return           The found FileConfiguration.
     */
    public FileConfiguration getConfig(String configName) {
        return configs.get(configName);
    }

}
