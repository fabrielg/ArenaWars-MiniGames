package me.aqua_tuor.arenawars.listeners;

import me.aqua_tuor.arenawars.managers.GameManager;
import me.aqua_tuor.arenawars.managers.GameState;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class PlayerDamageListener implements Listener {

    private GameManager gameManager;

    public PlayerDamageListener(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {

            Player player = (Player) event.getEntity();

            // Players can't take damage in lobby or starting
            if (gameManager.getGameState() == GameState.LOBBY || gameManager.getGameState() == GameState.STARTING || gameManager.getGameState() == GameState.TELEPORTING) {
                event.setCancelled(true);
            }

            // If player die in game, cancel and remove him a life.
            // Regen killer
            if (gameManager.getGameState() == GameState.INGAME) {
                if (player.getHealth() - event.getFinalDamage() <= 0) {
                    event.setCancelled(true);
                    player.setHealth(20);
                    int playerLives = gameManager.getPlayerManager().getPlayerLives().get(player);
                    gameManager.getPlayerManager().addPlayerLives(player, playerLives - 1);

                    StringBuilder sb = new StringBuilder();

                    Player killer = player.getKiller();
                    if (killer != null) {
                        killer.setHealth(20);
                        killer.sendMessage("§aYou killed " + player.getName());
                        sb.append("§cYou were killed by ").append(killer.getName());
                    } else {
                        sb.append("§cYou died");
                    }

                    // If player has no more lives, set gamemode to spectator and teleport him to the spawn
                    if (gameManager.getPlayerManager().getPlayerLives().get(player) <= 0) {
                        player.setGameMode(GameMode.SPECTATOR);
                        player.sendMessage(sb.append("§c and you have no more lives.").toString());
                        player.teleport(gameManager.getArenaManager().getArena().getSpawn());
                    }
                    // If player has lives, teleport him to a random respawns location
                    else {
                        player.sendMessage(sb.append("§c and you have " + gameManager.getPlayerManager().getPlayerLives().get(player) + " lives left.").toString());
                        player.teleport(gameManager.getArenaManager().getArena().getRandomRespawn());
                    }
                }
            }
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (event.getEntity() instanceof Player) {
            event.setCancelled(true);
        }
    }

}
