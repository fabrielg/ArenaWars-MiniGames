package me.aqua_tuor.arenawars;

import me.aqua_tuor.arenawars.commands.StartCommand;
import me.aqua_tuor.arenawars.listeners.BlockBreakListener;
import me.aqua_tuor.arenawars.listeners.BlockPlaceListener;
import me.aqua_tuor.arenawars.listeners.PlayerJoinQuitListener;
import me.aqua_tuor.arenawars.manager.GameManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class ArenaWars extends JavaPlugin {

    private GameManager gameManager;


    @Override
    public void onEnable() {
        // Plugin startup logic
        super.onEnable();
        this.gameManager = new GameManager(this);

        getServer().getPluginManager().registerEvents(new BlockBreakListener(gameManager), this);
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(gameManager), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinQuitListener(), this);

        Objects.requireNonNull(getCommand("start")).setExecutor(new StartCommand(gameManager));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}