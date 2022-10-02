package me.aqua_tuor.arenawars.tasks;

import me.aqua_tuor.arenawars.managers.GameManager;
import me.aqua_tuor.arenawars.managers.GameState;
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
            gameManager.setGameState(GameState.TELEPORTING);
            if (timer == 0) {
                Bukkit.broadcastMessage(gameManager.prefix + "§aTeleporting ... Get ready for battle!");
            }
            return;
        }

        if (timer == 10 || timer == 5 || timer == 4 || timer == 3 || timer == 2 || timer == 1) {
            Bukkit.broadcastMessage(gameManager.prefix + "You will be teleported in " + timer + " seconds!");
        }

        timer--;
    }
}
