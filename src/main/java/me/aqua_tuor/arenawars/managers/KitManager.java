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
        loadKits();
    }

    public void loadKits() {
        HashMap<String, Kit> kits = new HashMap<>();

        // Loop through all kits in the config
        for (String kitName : kitsConfig.getKeys(false)) {
            ConfigurationSection currentKitConfig = kitsConfig.getConfigurationSection(kitName);
            String icon = currentKitConfig.getString("icon");
            String displayName = currentKitConfig.getString("displayname");
            HashMap<Integer, ItemStack> items = new HashMap<>();

            // Add items to kit
            for (String item : currentKitConfig.getStringList("items")) {
                String[] itemSplit = item.split(",");
                ItemStack itemStack = new ItemStack(Material.getMaterial(itemSplit[0]));
                itemStack.setAmount(Integer.parseInt(itemSplit[1]));
                items.put(Integer.parseInt(itemSplit[2]), itemStack);
            }

            // Add armor to kit
            ItemStack[] armor = new ItemStack[4];
            armor[3] = new ItemStack(Material.getMaterial(currentKitConfig.getString("armor.helmet")));
            armor[2] = new ItemStack(Material.getMaterial(currentKitConfig.getString("armor.chestplate")));
            armor[1] = new ItemStack(Material.getMaterial(currentKitConfig.getString("armor.leggings")));
            armor[0] = new ItemStack(Material.getMaterial(currentKitConfig.getString("armor.boots")));

            // Create kit and add to kits
            Kit kit = new Kit(kitName, displayName, icon, items, armor);
            kits.put(kitName, kit);
        }

        this.kits = kits;
        System.out.println("Loaded " + kits.size() + " kits");
        System.out.println(kits);
    }

    public Inventory getKitGui() {
        Inventory inventory = gameManager.getPlugin().getServer().createInventory(null, 54, "Kits");
        for (Kit kit : kits.values()) {
            ItemStack itemStack = new ItemStack(Material.getMaterial(kit.getIcon()));
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName("§r" + kit.getDisplayName());
            itemStack.setItemMeta(itemMeta);
            inventory.addItem(itemStack);
        }
        return inventory;
    }

    public void openKitGUI(Player player) {
        player.openInventory(getKitGui());
    }

    public Kit getKit(String name) {
        return kits.get(name);
    }

    public Kit getKit(ItemStack icon) {
        for (Kit kit : kits.values()) {
            if (icon.getType() == Material.getMaterial(kit.getIcon())) {
                return kit;
            }
        }
        return null;
    }

    public HashMap<String, Kit> getKits() {
        return kits;
    }

}
