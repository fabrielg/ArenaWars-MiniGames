package me.aqua_tuor.arenawars.kits;

import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class Kit {

    private String name;
    private String icon;
    private HashMap<Integer, ItemStack> items;
    private ItemStack[] armor;

    public Kit(String name, String icon, HashMap<Integer, ItemStack> items, ItemStack[] armor) {
        this.name = name;
        this.icon = icon;
        this.items = items;
        this.armor = armor;
    }

    /** @return name of the kit */
    public String getName() {
        return name;
    }

    /** @return icon of the kit */
    public String getIcon() {
        return icon;
    }

    /** @return items string of the kit */
    public HashMap<Integer, ItemStack> getItems() {
        return items;
    }

    /** @return armor of the kit */
    public ItemStack[] getArmor() {
        return armor;
    }


}
