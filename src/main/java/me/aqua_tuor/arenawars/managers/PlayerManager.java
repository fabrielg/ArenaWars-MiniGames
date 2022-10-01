package me.aqua_tuor.arenawars.managers;

import me.aqua_tuor.arenawars.kits.Kit;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class PlayerManager {

    private final GameManager gameManager;
    private HashMap<Player, Kit> playerKits;

    public PlayerManager(GameManager gameManager, HashMap<Player, Kit> playerKits) {
        this.gameManager = gameManager;
        this.playerKits = playerKits;
    }

    public HashMap<Player, Kit> getPlayerKits() {
        return playerKits;
    }

    public void addPlayerKit(Player player, Kit kit) {
        if (playerKits.containsKey(player)) {
            playerKits.replace(player, kit);
        } else {
            playerKits.put(player, kit);
        }
    }

    public boolean giveKit(Player player, String kitName) {
        HashMap<String, Kit> kits = gameManager.getKitManager().getKits();
        if (kits.containsKey(kitName) && playerKits.containsKey(player) && player.getGameMode() == GameMode.SURVIVAL) {
            player.getInventory().clear();
            Kit kit = kits.get(kitName);
            for (int slot : kit.getItems().keySet()) {
                ItemStack itemStack = kit.getItems().get(slot);
                player.getInventory().setItem(slot, itemStack);
            }
            player.getInventory().setArmorContents(kit.getArmor());
            return true;
        }
        return false;
    }

    public void giveKits() {
        for (Player player : playerKits.keySet()) {
            System.out.println(playerKits);
            giveKit(player, playerKits.get(player).getName());
        }
    }


}
