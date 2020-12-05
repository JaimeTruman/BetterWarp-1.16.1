package es.warp.eventos;

import es.warp.objects.WarpManager;
import es.warp.objects.WarpPoint;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

public class PlayerInventoryClick implements Listener {
    public static final String title = ChatColor.DARK_RED + "" + ChatColor.BOLD + "             WARPS";

    @EventHandler
    public void onPlayerInventoryClick (InventoryClickEvent event) {
        try {
            if (!(event.getWhoClicked() instanceof Player) || event.getCurrentItem().getType().toString().equalsIgnoreCase("AIR")) {
                return;
            }
            String inventoryTitle = event.getView().getTitle();
            if (!inventoryTitle.equalsIgnoreCase(title)) {
                return;
            }
            event.setCancelled(true);
            ItemStack clickedItem = event.getCurrentItem();
            if (clickedItem.getItemMeta().getLore().get(4) == null) {
                return;
            }

            int id = Integer.parseInt(clickedItem.getItemMeta().getLore().get(4));
            Optional<WarpPoint> warpOptional = WarpManager.getWarpByID(id);
            if (warpOptional.isPresent()) {
                WarpPoint warp = warpOptional.get();

                Location warpLocation = new Location(Bukkit.getWorld(warp.wordlName), warp.x, warp.y, warp.z, warp.yaw, warp.pitch);
                Player player = (Player) event.getWhoClicked();

                player.closeInventory();
                player.teleport(warpLocation);
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 1);
            }

        }catch (NullPointerException ignored) {
            //IGNORED
        }
    }
}
