package com.moojm.wager;


import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class BarInventory {

    private Inventory inventory;
    public static final String SHOP_NAME = Utils.getColoredMessage("bar-name");

    public BarInventory() {
        int size = getInventorySize();
        inventory = Bukkit.createInventory(null, size, SHOP_NAME);
        addDrinks();
    }

    private int getInventorySize() {
        int numberOfContents = Drink.getDrinks().size();
        return (int) Math.ceil(numberOfContents / 9) * 9;

    }

    private void addDrinks() {
        for (Drink drink : Drink.getDrinks()) {
            inventory.setItem(drink.getSlot(), drink.getItem());
        }
    }

    public Inventory getInventory() {
        return inventory;
    }
}
