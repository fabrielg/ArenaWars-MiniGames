package me.aqua_tuor.arenawars.listeners;

import me.aqua_tuor.arenawars.kits.Kit;
import me.aqua_tuor.arenawars.managers.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

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
            if (event.getCurrentItem() != null) {
                Kit kit = gameManager.getKitManager().getKit(new ItemStack(event.getCurrentItem()));
                if (kit != null) {
                    player.closeInventory();
                    player.sendMessage(gameManager.getPrefix() + "§aYou have selected the §6" + kit.getDisplayName() + " §akit!");
                    gameManager.getPlayerManager().addPlayerKit(player, kit);
                }

            }
        }
    }


}
