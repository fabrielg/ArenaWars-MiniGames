package me.aqua_tuor.arenawars.tasks;

import me.aqua_tuor.arenawars.managers.GameManager;
import me.aqua_tuor.arenawars.managers.GameState;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class GameTeleportingCountdownTask extends BukkitRunnable {

    private final GameManager gameManager;

    public GameTeleportingCountdownTask(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    private int timer = 15;

    @Override
    public void run() {
        if (timer <= 0) {
            cancel();
            gameManager.setGameState(GameState.INGAME);
            if (timer == 0) {
                Bukkit.broadcastMessage(gameManager.getPrefix() + "§a§lGLHF !");
            }
            return;
        }

        if (timer == 15 || timer == 10 || timer == 5 || timer == 3 || timer == 2 || timer == 1) {
            Bukkit.broadcastMessage(gameManager.getPrefix() + "§7Be ready for battle in §e§l" + timer + " seconds§7");
        }

        timer--;
    }
}
