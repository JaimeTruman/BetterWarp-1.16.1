package es.warp.commands.actualCommands;

import es.warp.commands.Command;
import es.warp.objects.WarpManager;
import es.warp.objects.WarpPoint;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Optional;

public class DeleteWarpCommand extends Command {
    private final String commandName = "deletewarp";

    @Override
    public String getCommandName() {
        return commandName;
    }

    @Override
    public void executeCommand(Player player, String[] args) {
        if(!player.isOp()){
            player.sendMessage(ChatColor.DARK_RED + "Debes ser admin para poner este comando");
            return;
        }
        if(args.length != 1){
            player.sendMessage(ChatColor.DARK_RED + "Uso incorreto: /deletewarp <is>");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(args[0]);
        }catch (NumberFormatException e){
            player.sendMessage(ChatColor.DARK_RED + "Debes introducir un numero");
            return;
        }

        Optional<WarpPoint> warpToDelete = WarpManager.getWarpByID(id);
        if(!warpToDelete.isPresent()){
            player.sendMessage(ChatColor.DARK_RED + "Ese warp no existe");
            return;
        }

        WarpManager.deleteWarpByID(id);
        player.sendMessage(ChatColor.DARK_RED + "Has borrado el warp");
    }
}
