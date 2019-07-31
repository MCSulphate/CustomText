package me.sulphate.customtext.commands;

import me.sulphate.customtext.managers.CommandsManager;
import me.sulphate.customtext.managers.ConfigsManager;
import me.sulphate.customtext.utils.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
     * Command handler for the /customtext command. Has the following possibilities: reload,
     * list, add and remove. This will reload the configs, list the CustomText commands, add
     * a new CustomText command and remove a CustomText command.
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
            sender.sendMessage(Messages.INVALID_COMMAND);
            return true;
        }

        switch (args[0]) {

            case "reload": {
                // Reload the configs, then the loaded commands and messages.
                configsManager.reloadConfigs();
                commandsManager.reloadCommands();

                Messages.reloadMessages(configsManager);
                sender.sendMessage(Messages.CONFIGS_RELOADED);
                return true;
            }

            case "list": {
                Set<String> commandNames = commandsManager.getCommandNames();

                sender.sendMessage(Messages.LIST_MESSAGE);
                for (String commandName : commandNames) {
                    sender.sendMessage(Messages.LIST_MESSAGE_FORMAT.replace("[command]", commandName));
                }
                return true;
            }

            case "add": {
                if (args.length < 2) {
                    sender.sendMessage(Messages.NOT_ENOUGH_ARGS);
                    return true;
                }

                String commandName = args[1];

                if (commandsManager.isCustomTextCommand(commandName)) {
                    sender.sendMessage(Messages.COMMAND_ALREADY_EXISTS);
                    return true;
                }

                List<String> lines = parseArgs(args);
                commandsManager.addCommand(commandName, lines);
                sender.sendMessage(Messages.COMMAND_ADDED.replace("[command]", commandName));
                return true;
            }

            case "remove": {
                if (args.length < 2) {
                    sender.sendMessage(Messages.NOT_ENOUGH_ARGS);
                    return true;
                }

                String commandName = args[1];

                if (!commandsManager.isCustomTextCommand(commandName)) {
                    sender.sendMessage(Messages.COMMAND_DOES_NOT_EXIST);
                    return true;
                }

                commandsManager.removeCommand(commandName);
                sender.sendMessage(Messages.COMMAND_REMOVED.replace("[command]", commandName));
                return true;
            }

            default: {
                sender.sendMessage(Messages.INVALID_COMMAND);
                return true;
            }

        }
    }

    /**
     * Parses a list of arguments, grouping them into Strings by " " encapsulation.
     *
     * @param args The arguments to parse.
     * @return     The list of Strings, as parsed by the method.
     */
    private List<String> parseArgs(String[] args) {
        ArrayList<String> lines = new ArrayList<>();

        for (int i = 2; i < args.length; i++) {
            String arg = args[i];

            // If the argument starts with ", start looking for an arg ending with ".
            if (arg.startsWith("\"")) {
                arg = arg.substring(1);
                StringBuilder builder = new StringBuilder(arg);

                // Make sure we don't go over the bounds of the array. Using ++i for the first time!
                while (++i < args.length) {
                    String nextArg = args[i];
                    builder.append(' ');

                    // If it ends with ", then replace it, append it and break.
                    if (nextArg.endsWith("\"")) {
                        nextArg = nextArg.substring(0, nextArg.length() - 1);
                        builder.append(nextArg);

                        break;
                    }
                    else {
                        builder.append(nextArg);
                    }
                }

                // Add the result as a line.
                lines.add(builder.toString());
            }
            else {
                lines.add(arg);
            }
        }

        return lines;
    }
}
