package me.aqua_tuor.arenawars.managers;

import me.aqua_tuor.arenawars.kits.Kit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class KitManager {

    private ConfigurationSection kitsConfig;
    private final GameManager gameManager;
    private HashMap<String, Kit> kits = new HashMap<>();

    public KitManager(GameManager gameManager) {
        this.kitsConfig = gameManager.getPlugin().getConfig().getConfigurationSection("kits");
        this.gameManager = gameManager;
        this.kits = loadKits();
    }

    public HashMap<String, Kit> loadKits() {
        HashMap<String, Kit> kits = new HashMap<>();
        for (String kitName : kitsConfig.getKeys(false)) {
            String icon = kitsConfig.getString(kitName + ".icon");
            HashMap<Integer, ItemStack> items = new HashMap<>();
            for (String item : kitsConfig.getStringList(kitName + ".items")) {
                String[] itemSplit = item.split(",");
                ItemStack itemStack = new ItemStack(Material.getMaterial(itemSplit[0]));
                itemStack.setAmount(Integer.parseInt(itemSplit[1]));
                items.put(Integer.parseInt(itemSplit[2]), itemStack);
            }
            ItemStack[] armor = new ItemStack[4];
            armor[3] = new ItemStack(Material.getMaterial(kitsConfig.getString(kitName + ".armor.helmet")));
            armor[2] = new ItemStack(Material.getMaterial(kitsConfig.getString(kitName + ".armor.chestplate")));
            armor[1] = new ItemStack(Material.getMaterial(kitsConfig.getString(kitName + ".armor.leggings")));
            armor[0] = new ItemStack(Material.getMaterial(kitsConfig.getString(kitName + ".armor.boots")));
            Kit kit = new Kit(kitName, icon, items, armor);
            kits.put(kitName, kit);
        }
        return kits;
    }

    public Inventory getKitGui() {
        Inventory inventory = gameManager.getPlugin().getServer().createInventory(null, 54, "Kits");
        for (Kit kit : kits.values()) {
            ItemStack itemStack = new ItemStack(Material.getMaterial(kit.getIcon()));
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName("§r" + kit.getName());
            itemStack.setItemMeta(itemMeta);
            inventory.addItem(itemStack);
        }
        return inventory;
    }

    public void openKitGUI(Player player) {
        player.openInventory(getKitGui());
    }

    public HashMap<String, Kit> getKits() {
        return kits;
    }

}
