package me.aqua_tuor.arenawars.tasks;

import me.aqua_tuor.arenawars.manager.GameManager;
import me.aqua_tuor.arenawars.manager.GameState;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class GameStartCountdownTask extends BukkitRunnable {

    private final GameManager gameManager;

    public GameStartCountdownTask(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    private int timer = 10;

    @Override
    public void run() {
        if (timer <= 0) {
            cancel();
            gameManager.setGameState(GameState.INGAME);
            return;
        }

        if (timer == 10 || timer == 5 || timer == 4 || timer == 3 || timer == 2 || timer == 1) {
            Bukkit.broadcastMessage(gameManager.prefix + "The game will start in " + timer + " seconds!");
        }

        timer--;
    }
}
