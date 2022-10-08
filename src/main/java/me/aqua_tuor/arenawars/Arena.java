package me.aqua_tuor.arenawars;


import org.bukkit.Location;
import org.bukkit.World;

public class Arena {

    private String name;
    private World world;
    private Location spawn;
    private Location lobby;
    private Location[] region;
    private Location[] respawns;

    public Arena(String name, World world, Location spawn, Location lobby, Location[] region, Location[] respawns) {
        this.name = name;
        this.world = world;
        this.spawn = spawn;
        this.lobby = lobby;
        this.region = region;
        this.respawns = respawns;
    }

    /** @return name of the arena */
    public String getName() {
        return name;
    }

    /** @return world of the arena */
    public World getWorld() {
        return world;
    }

    /** @return spawn of the arena */
    public Location getSpawn() {
        return spawn;
    }

    /** @return lobby of the arena */
    public Location getLobby() {
        return lobby;
    }

    /** @return region of the arena */
    public Location[] getRegion() {
        return region;
    }

    /** @return respawns of the arena */
    public Location[] getRespawns() {
        return respawns;
    }

    public Location getRandomRespawn() {
        return respawns[(int) (Math.random() * respawns.length)];
    }
}
