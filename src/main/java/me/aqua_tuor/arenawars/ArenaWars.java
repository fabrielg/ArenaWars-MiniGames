package me.aqua_tuor.arenawars;

import me.aqua_tuor.arenawars.commands.KitsCommand;
import me.aqua_tuor.arenawars.commands.StartCommand;
import me.aqua_tuor.arenawars.listeners.*;
import me.aqua_tuor.arenawars.managers.GameManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class ArenaWars extends JavaPlugin {

    private GameManager gameManager;


    @Override
    public void onEnable() {
        // Plugin startup logic
        super.onEnable();
        this.gameManager = new GameManager(this);

        this.saveDefaultConfig();

        // Listeners
        getServer().getPluginManager().registerEvents(new BlockBreakListener(gameManager), this);
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(gameManager), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinQuitListener(gameManager), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractGuiKitsListener(gameManager), this);
        getServer().getPluginManager().registerEvents(new PlayerDropItemsListener(gameManager), this);
        getServer().getPluginManager().registerEvents(new PlayerInventoryInteractListener(gameManager), this);
        getServer().getPluginManager().registerEvents(new PlayerDamageListener(gameManager), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(gameManager), this);

        // Command Start
        Objects.requireNonNull(getCommand("start")).setExecutor(new StartCommand(gameManager));

        // Command Kits
        Objects.requireNonNull(getCommand("kits")).setExecutor(new KitsCommand(gameManager));


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
