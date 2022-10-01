package me.aqua_tuor.arenawars.listeners;

import me.aqua_tuor.arenawars.managers.GameManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractGuiKitsListener implements Listener {

    private GameManager gameManager;

    public PlayerInteractGuiKitsListener(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    public void onPlayerClickKit(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getInventory().equals(gameManager.getKitManager().getKitGui())) {
            // Check if the player has clicked on a kit
            if (event.getCurrentItem() != null || event.getCurrentItem().getType() != Material.AIR) {
                event.setCancelled(true);
                player.closeInventory();
                player.sendMessage(gameManager.prefix + "You have selected the " + event.getCurrentItem().getItemMeta().getDisplayName() + "&r kit!");
            }
        }

    }


}
