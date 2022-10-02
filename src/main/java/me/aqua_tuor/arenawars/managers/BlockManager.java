package me.aqua_tuor.arenawars.managers;

import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.HashSet;
import java.util.Set;

public class BlockManager {

    private final GameManager gameManager;
    private Set<Material> allowedBlocks = new HashSet<>();

    public BlockManager(GameManager gameManager) {
        this.gameManager = gameManager;
        this.allowedBlocks.add(Material.SPONGE);
    }

    public boolean isAllowed(Block block) {
        return allowedBlocks.contains(block.getType());
    }

}
