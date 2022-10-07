package me.aqua_tuor.arenawars.managers;

import me.aqua_tuor.arenawars.kits.Kit;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

import java.util.Arrays;
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
                // Set itemstack unbreakable and hide flags
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setUnbreakable(true);
                itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                itemStack.setItemMeta(itemMeta);
                player.getInventory().setItem(slot, itemStack);
            }
            // Set armor unbreakable and hide flags
            player.getInventory().setArmorContents(Arrays.stream(kit.getArmor()).map(itemStack -> {
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setUnbreakable(true);
                itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                itemStack.setItemMeta(itemMeta);
                return itemStack;
            }).toArray(ItemStack[]::new));

            // Add potion effects
            for (PotionEffect potionEffect : kit.getPotionEffects()) {
                player.addPotionEffect(potionEffect);
            }

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

    public void teleportPlayersToArena() {
        for (Player player: playerKits.keySet()) {
            player.teleport(gameManager.getArenaManager().getArena().getSpawn());
        }
    }

    public void giveLobbyItems(Player player) {
        if (player.getGameMode() != GameMode.CREATIVE) {
            player.getInventory().clear();
            ItemStack netherStar = new ItemStack(Material.NETHER_STAR);
            ItemMeta netherStarMeta = netherStar.getItemMeta();
            netherStarMeta.setDisplayName("§6Kit Selector");
            netherStar.setItemMeta(netherStarMeta);
            player.getInventory().setItem(4, netherStar);
        }
    }

    public void giveSpectatorItems(Player player) {
        if (player.getGameMode() != GameMode.CREATIVE) {
            player.getInventory().clear();
            ItemStack compass = new ItemStack(Material.COMPASS);
            ItemMeta compassMeta = compass.getItemMeta();
            compassMeta.setDisplayName("§6Spectator Selector");
            compass.setItemMeta(compassMeta);
            player.getInventory().setItem(4, compass);
        }
    }

    public void setPlayerState(Player player, GameState gameState) {
        if (Bukkit.getOnlinePlayers().size() < gameManager.getMaxPlayers() && (gameState == GameState.LOBBY || gameState == GameState.STARTING)) {
            giveLobbyItems(player);
            player.teleport(gameManager.getArenaManager().getArena().getLobby());
            player.setGameMode(GameMode.SURVIVAL);
            player.updateInventory();
            player.setLevel(0);
            player.setExp(0);
            player.setHealth(20);
            player.setFoodLevel(20);
            player.getActivePotionEffects().forEach(potionEffect -> player.removePotionEffect(potionEffect.getType()));
        } else {
            giveSpectatorItems(player);
            player.teleport(gameManager.getArenaManager().getArena().getSpawn());
            player.setGameMode(GameMode.SPECTATOR);
            player.getActivePotionEffects().forEach(potionEffect -> player.removePotionEffect(potionEffect.getType()));
        }

    }

}
