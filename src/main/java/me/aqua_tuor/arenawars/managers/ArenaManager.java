package me.aqua_tuor.arenawars.managers;

import me.aqua_tuor.arenawars.Arena;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;

public class ArenaManager {

    private final GameManager gameManager;
    private Arena arena;
    private ConfigurationSection arenaConfig;

    public ArenaManager(GameManager gameManager) {
        arenaConfig = gameManager.getPlugin().getConfig().getConfigurationSection("arena");
        this.gameManager = gameManager;
        loadArena();
    }

    public void loadArena() {
        String name = arenaConfig.getString("name");
        World world = gameManager.getPlugin().getServer().getWorld(arenaConfig.getString("world"));
        String[] spawnString = arenaConfig.getString("spawn").split(",");
        Location spawn = new Location(world, Double.parseDouble(spawnString[0]), Double.parseDouble(spawnString[1]), Double.parseDouble(spawnString[2]), Float.parseFloat(spawnString[3]), Float.parseFloat(spawnString[4]));
        String[] lobbyString = arenaConfig.getString("lobby").split(",");
        Location lobby = new Location(world, Double.parseDouble(lobbyString[0]), Double.parseDouble(lobbyString[1]), Double.parseDouble(lobbyString[2]), Float.parseFloat(lobbyString[3]), Float.parseFloat(lobbyString[4]));
        String[] regionStringMin = arenaConfig.getString("region.min").split(",");
        String[] regionStringMax = arenaConfig.getString("region.max").split(",");
        Location[] region = new Location[2];
        region[0] = new Location(world, Double.parseDouble(regionStringMin[0]), Double.parseDouble(regionStringMin[1]), Double.parseDouble(regionStringMin[2]));
        region[1] = new Location(world, Double.parseDouble(regionStringMax[0]), Double.parseDouble(regionStringMax[1]), Double.parseDouble(regionStringMax[2]));
        arena = new Arena(name, world, spawn, lobby, region);
    }

    public Arena getArena() {
        return arena;
    }


}
