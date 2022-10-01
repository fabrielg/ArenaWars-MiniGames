package me.aqua_tuor.arenawars.listeners;

import me.aqua_tuor.arenawars.managers.GameManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    private final GameManager gameManager;

    public BlockBreakListener(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (!gameManager.getBlockManager().isAllowed(event.getBlock()) && !event.getPlayer().getGameMode().name().equals("CREATIVE")) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("You can't break this block!");
        }
    }

}
