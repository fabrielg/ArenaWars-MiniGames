package me.aqua_tuor.arenawars.kits;

import java.util.HashMap;
import java.util.List;

public class Kit {

    private String name;
    private String icon;
    private HashMap<Integer, String> items;

    public Kit(String name, String icon, HashMap<Integer, String> items) {
        this.name = name;
        this.icon = icon;
        this.items = items;
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
    public HashMap<Integer, String> getItems() {
        return items;
    }


}
