package me.sulphate.customtext.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import me.sulphate.customtext.CustomText;
import me.sulphate.customtext.managers.CommandsManager;
import me.sulphate.customtext.utils.GeneralUtils;
import me.sulphate.customtext.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

import java.util.List;

/**
 * Handles the execution of CustomText commands. Will ensure the command doesn't already
 * exist.
 */
public class CommandListener implements Listener {

    private CommandsManager commandsManager;

    /**
     * Initialises the listener with an instance of CommandsManager.
     *
     * @param commandsManager The instance of the commands manager.
     * @see   CommandsManager
     */
    public CommandListener(CommandsManager commandsManager) {
        this.commandsManager = commandsManager;
    }

    /**
     * The event handler. Checks outgoing commands, if any of them are CustomText commands
     * it will ensure they don't belong to a loaded plugin, and then will send the lines of
     * text for that command to the Player.
     *
     * @param event The Event object that was created when the event fired.
     */
    @EventHandler
    public void onEvent(PlayerCommandPreprocessEvent event) {
        String message = event.getMessage();

        String command = message.split(" ")[0].replace("/", "");
        if (commandsManager.isCustomTextCommand(command)) {
            // Check that the command doesn't belong to another plugin.
            for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
                if (plugin.getDescription().getCommands().containsKey(command)) {
                    // Send warning message, return.
                    GeneralUtils.sendConsoleMessage(Messages.COMMAND_IN_OTHER_PLUGIN.replace("[command]", command));
                    return;
                }
            }

            // Cancel the event.
            event.setCancelled(true);

            // Send the lines, parsing any placeholders with PAPI, if it is present.
            List<String> lines = commandsManager.getLinesForCommand(command);

            for (String line : lines) {
                Player player = event.getPlayer();

                line = GeneralUtils.colourise(line);

                // Parse placeholders if PAPI is hooked.
                if (CustomText.getPlugin().isPAPIHooked()) {
                    line = PlaceholderAPI.setPlaceholders(player, line);
                }

                player.sendMessage(line);
            }
        }
    }

}
