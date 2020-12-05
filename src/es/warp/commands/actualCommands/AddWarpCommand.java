package es.warp.commands.actualCommands;

import es.warp.commands.Command;
import es.warp.objects.WarpManager;
import es.warp.objects.WarpPoint;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class AddWarpCommand extends Command {
    private String commandName;

    @Override
    public String getCommandName() {
        return commandName;
    }

    @Override
    public void executeCommand(Player player, String[] args) {
        if(!player.isOp()){
            player.sendMessage(ChatColor.DARK_RED + "Ese comando solo lo pueden poner los admins");
            return;
        }
        if(args.length != 1) {
            player.sendMessage(ChatColor.DARK_RED + "Uso incorrecto: /addwarp <nombre>");
            return;
        }
        if(player.getInventory().getItemInMainHand() == null || player.getInventory().getItemInMainHand().getType().toString().equalsIgnoreCase("AIR")){
            player.sendMessage(ChatColor.DARK_RED + "Debes tener un item en la mano");
            return;
        }

        String nombreWarp = args[0].toUpperCase();
        String nombreItem = player.getInventory().getItemInMainHand().getType().toString();

        WarpPoint warpPoint = new WarpPoint(player.getLocation(), nombreItem, nombreWarp);
        WarpManager.addWarp(warpPoint);

        player.sendMessage(ChatColor.GREEN + "Has añadido un nuevo punto de warp");
    }
}
