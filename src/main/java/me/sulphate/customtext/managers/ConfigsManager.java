package me.sulphate.customtext.managers;

import me.sulphate.customtext.utils.ConfigUtils;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

/**
 * Manages loading/saving of configs from the file system.
 */
public class ConfigsManager {

    private HashMap<String, FileConfiguration> configs;

    /**
     * Loads all configs from the file system.
     */
    public ConfigsManager() {
        configs = new HashMap<>();
        String[] configNames = { "commands.yml", "messages.yml" };

        for (String configName : configNames) {
            configs.put(configName, ConfigUtils.loadConfig(configName));
        }
    }

    /**
     * Reloads a config from the file system.
     *
     * @param configName The filename of the config to reload.
     */
    public void reloadConfig(String configName) {
        configs.remove(configName);
        configs.put(configName, ConfigUtils.loadConfig(configName));
    }

    /**
     * Returns an Object value from a config by a key path.
     *
     * @param configName The name of the config that contains the value.
     * @param key        The key of the key/value pair in the config.
     * @return           The value found, as an Object.
     */
    public Object getConfigValue(String configName, String key) {
        return configs.get(configName).get(key);
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
