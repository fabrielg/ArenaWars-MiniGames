package me.aqua_tuor.arenawars.listeners;

import me.aqua_tuor.arenawars.managers.GameManager;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class PlayerInventoryInteractListener implements Listener {

    private GameManager gameManager;

    public PlayerInventoryInteractListener(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    public void onPlayerInteractInventory(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (player.getGameMode() != GameMode.CREATIVE) {
            event.setCancelled(true);
            player.updateInventory();
        }
    }

}
