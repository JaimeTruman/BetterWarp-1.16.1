package es.warp.commands.actualCommands;

import es.warp.commands.Command;
import es.warp.eventos.PlayerInventoryClick;
import es.warp.objects.WarpManager;
import es.warp.objects.WarpPoint;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class WarpCommand extends Command {
    private final String commandName = "warp";

    @Override
    public String getCommandName() {
        return commandName;
    }

    @Override
    public void executeCommand(Player player, String[] args) {
        Inventory inventory = Bukkit.createInventory(null, 27, PlayerInventoryClick.title);
        Set<WarpPoint> warps = WarpManager.getCopyOfWarps();

        warps.forEach( (warpIte) -> {
            ItemStack warpItem = new ItemStack(Material.getMaterial(warpIte.itemName));
            ItemMeta warpItemMeta = warpItem.getItemMeta();

            warpItemMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "" + warpIte.name);
            List<String> lore = new ArrayList<>();
            lore.add("   ");
            lore.add(ChatColor.GOLD + "Click para teletransportarse");
            lore.add(ChatColor.GOLD + "" + warpIte.x + ", " + warpIte.y + ", " + warpIte.z);
            lore.add("    ");
            lore.add(warpIte.id + "");

            warpItemMeta.setLore(lore);
            warpItem.setItemMeta(warpItemMeta);

            inventory.addItem(warpItem);
        });

        player.openInventory(inventory);
    }
}