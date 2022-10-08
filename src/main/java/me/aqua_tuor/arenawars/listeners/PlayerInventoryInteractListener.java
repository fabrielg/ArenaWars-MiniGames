package me.aqua_tuor.arenawars.listeners;

import me.aqua_tuor.arenawars.managers.GameManager;
import me.aqua_tuor.arenawars.managers.GameState;
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

        // Players can't interact with their inventory, only in creative mode or in game
        if (player.getGameMode() != GameMode.CREATIVE && gameManager.getGameState() != GameState.INGAME && gameManager.getGameState() != GameState.TELEPORTING && gameManager.getPlayerManager().getPlayerKits().containsKey(player)) {
            event.setCancelled(true);
            player.updateInventory();
        }
    }

}
