package me.sulphate.customtext.utils;

import me.sulphate.customtext.CustomText;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Contains utility methods for loading configs & files, and for
 * saving default resources.
 */
public class ConfigUtils {

    /**
     * Loads a FileConfiguration from a file, saving the default resource if
     * it doesn't already exist.
     *
     * @param configName The filename of the config to load.
     * @return           The loaded FileConfiguration.
     */
    public static FileConfiguration loadConfig(String configName) {
        File dataFolder = CustomText.getPlugin().getDataFolder();
        dataFolder.mkdir();

        File configFile = new File(dataFolder, configName);

        // If the config does not exist, save the default resource.
        if (!configFile.exists()) {
            CustomText.getPlugin().saveResource(configName, true);
        }

        return YamlConfiguration.loadConfiguration(configFile);
    }

    /**
     * Saves a FileConfiguration to file.
     *
     * @param config   The FileConfiguration to save.
     * @param fileName The filename to save the config to.
     */
    public static void saveConfig(FileConfiguration config, String fileName) {
        File fileToSaveTo = new File(CustomText.getPlugin().getDataFolder(), fileName);

        try {
            config.save(fileToSaveTo);
        }
        catch (IOException ex) {
            // TODO: Send error message.
        }
    }

}
