package me.aqua_tuor.arenawars.kits;

import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;

public class Kit {

    private String name;
    private String displayName;
    private String icon;
    private HashMap<Integer, ItemStack> items;
    private ItemStack[] armor;
    private PotionEffect[] potionEffects;

    public Kit(String name, String displayName, String icon, HashMap<Integer, ItemStack> items, ItemStack[] armor, PotionEffect[] potionEffects) {
        this.name = name;
        this.displayName = displayName;
        this.icon = icon;
        this.items = items;
        this.armor = armor;
        this.potionEffects = potionEffects;
    }

    /** @return name of the kit */
    public String getName() {
        return name;
    }

    /** @return display name of the kit */
    public String getDisplayName() {
        return displayName;
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

    /** @return potion effects of the kit */
    public PotionEffect[] getPotionEffects() {
        return potionEffects;
    }


}
