package me.aqua_tuor.arenawars.listeners;

import me.aqua_tuor.arenawars.managers.GameManager;
import me.aqua_tuor.arenawars.managers.GameState;
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
        gameManager.getPlayerManager().setPlayerState(player, gameManager.getGameState());

        if (gameManager.getGameState() == GameState.LOBBY || gameManager.getGameState() == GameState.STARTING) {
            Bukkit.broadcastMessage("§6" + player.getName() + " §ejoined the game! §8(§6" + Bukkit.getOnlinePlayers().size() + "§8/§c" + gameManager.getMaxPlayers() + "§8)");
            gameManager.getPlayerManager().addPlayerKit(player, gameManager.getKitManager().getKit("Default"));
            gameManager.getPlayerManager().addPlayerLives(player, 3);
        }

        // Check if players count is enough to start the game
        if (gameManager.getGameState() == GameState.LOBBY) {
            if (Bukkit.getOnlinePlayers().size() >= gameManager.getMinPlayers()) {
                gameManager.setGameState(GameState.STARTING);
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        // Cancel quit message
        event.setQuitMessage(null);

        Player player = event.getPlayer();
        if (gameManager.getGameState() == GameState.LOBBY || gameManager.getGameState() == GameState.STARTING) {
            Bukkit.broadcastMessage("§6" + player.getName() + " §eleft the game! §8(§6" + Bukkit.getOnlinePlayers().size() + "§8/§c" + gameManager.getMaxPlayers() + "§8)");
        }
        gameManager.getPlayerManager().getPlayerKits().remove(player);
        gameManager.getPlayerManager().getPlayerLives().remove(player);
    }

}
