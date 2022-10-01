package me.aqua_tuor.arenawars.commands;

import me.aqua_tuor.arenawars.managers.GameManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KitsCommand implements CommandExecutor {

    private final GameManager gameManager;

    public KitsCommand(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        gameManager.getKitManager().openKitGUI(player);
        return true;
    }
}
