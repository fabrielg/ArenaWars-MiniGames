package me.aqua_tuor.arenawars.managers;

import me.aqua_tuor.arenawars.ArenaWars;
import me.aqua_tuor.arenawars.kits.Kit;
import me.aqua_tuor.arenawars.tasks.GameProgressCountdownTask;
import me.aqua_tuor.arenawars.tasks.GameStartCountdownTask;
import me.aqua_tuor.arenawars.tasks.GameTeleportingCountdownTask;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class GameManager {

    private final ArenaWars plugin;
    public GameState gameState = GameState.LOBBY;
    private BlockManager blockManager;
    private PlayerManager playerManager;
    private KitManager kitManager;
    private ArenaManager arenaManager;

    private GameStartCountdownTask gameStartCountdownTask;
    private GameTeleportingCountdownTask gameTeleportingCountdownTask;
    private GameProgressCountdownTask gameProgressCountdownTask;

    private int maxPlayers;
    private int minPlayers;

    public String prefix = "§8[§6ArenaWars§8]§r ";

    public GameManager(ArenaWars plugin) {
        this.plugin = plugin;
        this.blockManager = new BlockManager(this);
        this.playerManager = new PlayerManager(this, new HashMap<Player, Kit>(), new HashMap<Player, Integer>());
        this.kitManager = new KitManager(this);
        this.arenaManager = new ArenaManager(this);
        this.maxPlayers = plugin.getConfig().getInt("game.max-players");
        this.minPlayers = plugin.getConfig().getInt("game.min-players");
    }

    public void setGameState(GameState gameState) {
        if (this.gameState == gameState) return;
        this.gameState = gameState; // set the game state
        switch (gameState) {
            case LOBBY:
                if (getPlayerManager().getPlayerKits().size() >= minPlayers) {
                    setGameState(GameState.STARTING);
                }
                break;
            case STARTING:
                this.gameStartCountdownTask = new GameStartCountdownTask(this);
                this.gameStartCountdownTask.runTaskTimer(plugin, 0, 20);
                break;
            case TELEPORTING:
                // Teleport players to the arena and give their kits
                this.getPlayerManager().teleportPlayersToArena();
                this.getPlayerManager().giveKits();

                // Send title to all players
                for (Player player : this.getPlayerManager().getPlayerKits().keySet()) {
                    player.sendTitle("§6Teleporting...", "§7Be ready for battle in §e§l15 seconds§7", 10, 70, 20);
                }
                this.gameTeleportingCountdownTask = new GameTeleportingCountdownTask(this);
                this.gameTeleportingCountdownTask.runTaskTimer(plugin, 0, 20);
                break;
            case INGAME:
                this.gameProgressCountdownTask = new GameProgressCountdownTask(this);
                this.gameProgressCountdownTask.runTaskTimer(plugin, 0, 20);
                break;
            case WON:
                Bukkit.broadcastMessage(prefix + "§aThe game has ended! The winner is " + checkWinner().getName() + "!");
                setGameState(GameState.RESTARTING);
                break;
            case RESTARTING:
                Bukkit.broadcastMessage(prefix + "§aTeleportation to Lobby");

                // Clear playerlives
                this.getPlayerManager().getPlayerLives().clear();

                for (Player player : getPlayerManager().getPlayerKits().keySet()) {
                    player.teleport(getArenaManager().getArena().getLobby());
                    this.getPlayerManager().setPlayerState(player, getGameState());
                    this.getPlayerManager().getPlayerLives().put(player, 3);
                }
                setGameState(GameState.LOBBY);
                break;
        }
    }

    public BlockManager getBlockManager() {
        return blockManager;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public GameStartCountdownTask getGameStartCountdownTask() {
        return gameStartCountdownTask;
    }

    public GameTeleportingCountdownTask getGameTeleportingCountdownTask() {
        return gameTeleportingCountdownTask;
    }

    public GameProgressCountdownTask getGameProgressCountdownTask() {
        return gameProgressCountdownTask;
    }

    public KitManager getKitManager() {
        return kitManager;
    }

    public ArenaManager getArenaManager() {
        return arenaManager;
    }

    public String getPrefix() {
        return prefix;
    }

    public ArenaWars getPlugin() {
        return plugin;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public GameState getGameState() {
        return gameState;
    }

    public Player checkWinner() {
        Player winner = null;
        for (Player player : this.getPlayerManager().getPlayerLives().keySet()) {
            // Check if the only one player has lives left
            if (this.getPlayerManager().getPlayerLives().get(player) > 0) {
                if (winner == null) {
                    winner = player;
                } else {
                    return null;
                }
            }
        }
        return winner;
    }
}
