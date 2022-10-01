package me.aqua_tuor.arenawars.listeners;

import me.aqua_tuor.arenawars.managers.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinQuitListener implements Listener {

    private GameManager gameManager;

    public PlayerJoinQuitListener(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Cancel join message
        event.setJoinMessage(null);

        Player player = event.getPlayer();
        Bukkit.broadcastMessage("§6" + player.getName() + " §ejoined the game! §8(§6" + Bukkit.getOnlinePlayers().size() + "§8/§c" + Bukkit.getMaxPlayers() + "§8)");
        gameManager.getPlayerManager().addPlayerKit(player, gameManager.getKitManager().getKits().get("Barbarian"));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        // Cancel quit message
        event.setQuitMessage(null);

        Player player = event.getPlayer();
        Bukkit.broadcastMessage("§6" + player.getName() + " §eleft the game! §8(§6" + (Bukkit.getOnlinePlayers().size() - 1) + "§8/§c" + Bukkit.getMaxPlayers() + "§8)");
        gameManager.getPlayerManager().getPlayerKits().remove(player);
    }

}
