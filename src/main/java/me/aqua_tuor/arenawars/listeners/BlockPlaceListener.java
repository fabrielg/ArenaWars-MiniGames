package me.aqua_tuor.arenawars.listeners;

import me.aqua_tuor.arenawars.manager.GameManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

    private final GameManager gameManager;

    public BlockPlaceListener(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (!gameManager.getBlockManager().isAllowed(event.getBlock()) && !event.getPlayer().getGameMode().name().equals("CREATIVE")) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("You can't place this block!");
        }
    }

}
