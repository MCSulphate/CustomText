package me.sulphate.customtext.commands;

import me.sulphate.customtext.managers.CommandsManager;
import me.sulphate.customtext.managers.ConfigsManager;
import me.sulphate.customtext.utils.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CustomTextCommand implements CommandExecutor {

    private CommandsManager commandsManager;
    private ConfigsManager configsManager;

    /**
     * Initialises the command with the instance of CommandsManager and ConfigsManager.
     *
     * @param commandsManager The instance of CommandsManager.
     * @param configsManager  The instance of ConfigsManager.
     *
     * @see   CommandsManager
     * @see   ConfigsManager
     */
    public CustomTextCommand(CommandsManager commandsManager, ConfigsManager configsManager) {
        this.commandsManager = commandsManager;
        this.configsManager = configsManager;
    }

    /**
     * Command handler for the /customtext command.
     *
     * @param sender  The sender of the command, usually a Player.
     * @param command The command that was sent (customtext).
     * @param label   The command label.
     * @param args    The arguments passed to the command.
     *
     * @return        Whether the command was successful or not (always true).
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("customtext.admin")) {
            sender.sendMessage(Messages.NO_PERMISSION);
            return true;
        }

        if (args.length < 1) {
            sender.sendMessage(Messages.NOT_ENOUGH_ARGS);
            return true;
        }

        switch (args[0]) {

            case "reload": {
                return true;
            }

            case "list": {
                return true;
            }

            case "add": {
                return true;
            }

            case "remove": {
                return true;
            }

            default: {
                return true;
            }

        }
    }
}
