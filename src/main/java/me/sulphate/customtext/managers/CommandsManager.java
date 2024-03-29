package me.sulphate.customtext.managers;

import me.sulphate.customtext.utils.GeneralUtils;
import me.sulphate.customtext.utils.Messages;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Manages loaded custom commands.
 */
public class CommandsManager {

    private HashMap<String, List<String>> commands;
    private ConfigsManager configsManager;

    /**
     * Initialises the CommandsManager with an instance of ConfigsManager.
     *
     * @param configsManager The instance of ConfigsManager.
     */
    public CommandsManager(ConfigsManager configsManager) {
        this.configsManager = configsManager;
        reloadCommands();
    }

    /**
     * Reloads all existing commands from config.
     */
    public void reloadCommands() {
        FileConfiguration config = configsManager.getConfig("commands.yml");

        int numberOfCommands = 0;
        commands = new HashMap<>();
        for (String commandName : config.getKeys(false)) {
            List<String> lines = config.getStringList(commandName);
            commands.put(commandName, lines);
            numberOfCommands++;
        }

        GeneralUtils.sendConsoleMessage(Messages.LOADED_COMMANDS.replace("[amount]", numberOfCommands + ""));
    }

    /**
     * Returns whether a command is a registered CustomText command.
     *
     * @param commandName The name of the command to check.
     * @return            Whether the command is registered with CustomText.
     */
    public boolean isCustomTextCommand(String commandName) {
        return commands.containsKey(commandName);
    }

    /**
     * Gets the list of lines of text to send for a command.
     *
     * @param commandName The name of the CustomText command.
     * @return            The lines of text to send.
     */
    public List<String> getLinesForCommand(String commandName) {
        return commands.get(commandName);
    }

    /**
     * Adds a new CustomText command.
     *
     * @param commandName The name of the command to add.
     * @param lines       The lines of text to display when the command is run.
     */
    public void addCommand(String commandName, List<String> lines) {
        commands.put(commandName, lines);
        configsManager.setConfigValue("commands.yml", commandName, lines);
    }

    /**
     * Removes a CustomText command.
     *
     * @param commandName The name of the command to remove.
     */
    public void removeCommand(String commandName) {
        commands.remove(commandName);
        configsManager.setConfigValue("commands.yml", commandName, null);
    }

    /**
     * Returns a set of command names for CustomText commands.
     *
     * @return The set of command names.
     */
    public Set<String> getCommandNames() {
        return commands.keySet();
    }

}
