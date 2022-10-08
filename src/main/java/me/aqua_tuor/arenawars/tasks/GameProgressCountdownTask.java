package me.aqua_tuor.arenawars.tasks;

import me.aqua_tuor.arenawars.managers.GameManager;
import me.aqua_tuor.arenawars.managers.GameState;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class GameProgressCountdownTask extends BukkitRunnable {

    private GameManager gameManager;

    public GameProgressCountdownTask(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    // The time in seconds for 10 minutes
    private int time = 600;

    @Override
    public void run() {
        if (time == 0) {
            gameManager.setGameState(GameState.WON);
            return;
        }

        if (time % 60 == 0) {
            // Every minute
            Bukkit.broadcastMessage(gameManager.getPrefix() + "§eThe game will end in §6" + time / 60 + " §eminutes!");
        } else if (time == 30 || time == 15 || time == 10 || time == 5 || time == 4 || time == 3 || time == 2 || time == 1) {
            // 30 seconds, 15 seconds, 10 seconds, 5 seconds, 4 seconds, 3 seconds, 2 seconds and 1 second
            Bukkit.broadcastMessage(gameManager.getPrefix() + "§eThe game will end in §6" + time + " §eseconds!");
        }

        time--;
    }
}
