package me.aqua_tuor.arenawars.listeners;

import me.aqua_tuor.arenawars.managers.GameManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class PlayerInteractGuiKitsListener implements Listener {

    private GameManager gameManager;

    public PlayerInteractGuiKitsListener(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    public void onPlayerClickKit(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        // Check if player's inventory is the kit inventory
        if (event.getView().getTitle().equals("Kits")) {
            event.setCancelled(true);
            player.updateInventory();
            // Check if player clicked on a kit
            if (event.getCurrentItem() != null && gameManager.getKitManager().getKits().containsKey(event.getCurrentItem().getItemMeta().getDisplayName())) {

                player.closeInventory();
                player.sendMessage(gameManager.getPrefix() + "§aYou have selected the §6" + event.getCurrentItem().getItemMeta().getDisplayName() + " §akit!");
                gameManager.getPlayerManager().addPlayerKit(player, gameManager.getKitManager().getKit(event.getCurrentItem().getItemMeta().getDisplayName()));
            }
        }
    }


}
