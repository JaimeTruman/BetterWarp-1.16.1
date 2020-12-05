package es.warp.commands;

import es.warp.commands.actualCommands.AddWarpCommand;
import es.warp.commands.actualCommands.DeleteWarpCommand;
import es.warp.commands.actualCommands.WarpCommand;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

public class CommandManager implements CommandExecutor {
    public Map<String,Command> commandsAvailable = new HashMap<>();

    public CommandManager () {
        commandsAvailable.put("warp", new WarpCommand());
        commandsAvailable.put("addwarp", new AddWarpCommand());
        commandsAvailable.put("deletewarp", new DeleteWarpCommand());
    }


    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String s, String[] args) {
        if(!(sender instanceof Player)){
            return true;
        }

        Command typedCommand = commandsAvailable.get(command.getName().toLowerCase());
        if(typedCommand != null)
            typedCommand.executeCommand((Player) sender, args);

        return true;
    }
}