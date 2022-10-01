package me.aqua_tuor.arenawars.managers;

import jdk.internal.org.jline.reader.ConfigurationPath;
import me.aqua_tuor.arenawars.ArenaWars;
import me.aqua_tuor.arenawars.tasks.GameStartCountdownTask;

public class GameManager {

    private final ArenaWars plugin;
    public GameState gameState = GameState.LOBBY;
    private BlockManager blockManager;
    private PlayerManager playerManager;
    private GameStartCountdownTask gameStartCountdownTask;
    private KitManager kitManager;

    public String prefix = "§8[§6ArenaWars§8]§r ";

    public GameManager(ArenaWars plugin) {
        this.plugin = plugin;
        this.blockManager = new BlockManager(this);
        this.playerManager = new PlayerManager(this);
        this.playerManager = new PlayerManager(this);
        this.kitManager = new KitManager(this);
    }

    public void setGameState(GameState gameState) {
        if (this.gameState == gameState) return;
        this.gameState = gameState; // set the game state
        switch (gameState) {
            case LOBBY:
                // TODO: Cancel player log in
                break;
            case STARTING:
                // TODO: Start the game (countdown)
                this.gameStartCountdownTask = new GameStartCountdownTask(this);
                this.gameStartCountdownTask.runTaskTimer(plugin, 0, 20);
                break;
            case INGAME:
                // TODO: Teleport players to the arena and give their kits
                this.getPlayerManager().giveKits();
                break;
            case WON:
                // TODO: End the game and teleport players back to the lobby
                break;
            case RESTARTING:
                // TODO: Restart the game (countdown)
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

    public KitManager getKitManager() {
        return kitManager;
    }

    public String getPrefix() {
        return prefix;
    }

    public ArenaWars getPlugin() {
        return plugin;
    }
}
