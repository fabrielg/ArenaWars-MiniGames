package me.aqua_tuor.arenawars.managers;

import me.aqua_tuor.arenawars.kits.Kit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Item;
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
            HashMap<Integer, String> items = new HashMap<>();
            for (String item : kitsConfig.getStringList(kitName + ".items")) {
                String[] itemSplit = item.split(",");
                items.put(Integer.parseInt(itemSplit[1]), itemSplit[0]);
            }
            Kit kit = new Kit(kitName, icon, items);
            kits.put(kitName, kit);
        }
        System.out.println(kits);
        System.out.println(kitsConfig);
        return kits;
    }

    public void openKitGUI(Player player) {
        Inventory inventory = gameManager.getPlugin().getServer().createInventory(null, 54, "Kits");
        for (Kit kit : kits.values()) {
            ItemStack itemStack = new ItemStack(Material.getMaterial(kit.getIcon()));
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(kit.getName());
            itemStack.setItemMeta(itemMeta);
            inventory.addItem(itemStack);
        }
        player.openInventory(inventory);
    }

}
