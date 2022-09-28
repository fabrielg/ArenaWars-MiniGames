package me.aqua_tuor.arenawars.commands;

import me.aqua_tuor.arenawars.manager.GameManager;
import me.aqua_tuor.arenawars.manager.GameState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StartCommand implements CommandExecutor {

    private final GameManager gameManager;
    public StartCommand(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        gameManager.setGameState(GameState.STARTING);
        return true;
    }
}
