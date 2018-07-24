package com.moojm.wager;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Drink {

    private static List<Drink> drinks = new ArrayList<>();

    private int slot;
    private ItemStack item;
    private double price;
    private double reward;
    private double chance;

    public Drink(int slot, ItemStack item, double price, double reward, double chance) {
        this.slot = slot;
        this.item = item;
        this.price = price;
        this.reward = reward;
        this.chance = chance;
        drinks.add(this);
    }

    public boolean roll() {
        Random random = new Random();
        int num = random.nextInt(100) + 1;
        if (num < chance) {
            return true;
        }
        return false;
    }

    public static Drink getDrinkFromItemStack(ItemStack item) {
        for (Drink drink : drinks) {
            if (drink.getItem().isSimilar(item)) {
                return drink;
            }
        }
        return null;
    }

    public static void loadDrinks() {
        ConfigurationSection items = Utils.getConfig().getConfigurationSection("items");
        for (String slot : items.getKeys(false)) {
            int numSlot = Integer.valueOf(slot);
            Drink drink = getDrinkFromSlotSection(numSlot);
            drinks.add(drink);
        }
    }

    private static Drink getDrinkFromSlotSection(int slot) {
        String path = "items." + slot;
        ConfigurationSection slotSection = Utils.getConfig().getConfigurationSection(path);
        String displayName = Utils.getConfig().getString(path + ".name");
        double price = Utils.getConfig().getDouble(path + ".price");
        double reward = Utils.getConfig().getDouble(path + ".reward");
        double chance = Utils.getConfig().getDouble(path + ".chance");
        Material material = Material.getMaterial(Utils.getConfig().getString(path + ".material"));
        ItemStack item = buildItemStack(displayName, price, reward, chance, material);
        return new Drink(slot, item, price, reward, chance);
    }

    private static ItemStack buildItemStack(String displayName, double price, double reward, double chance, Material material) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Utils.toColor(displayName));
        List<String> lore = buildLore(price, reward, chance);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    private static List<String> buildLore(double price, double reward, double chance) {
        List<String> lore = new ArrayList<>();
        String sPrice = Utils.convertMoney(price);
        String sReward = Utils.convertMoney(reward);
        lore.add(Utils.getColoredMessage("price-lore").replace("{price}", sPrice) + " Bits");
        lore.add(Utils.getColoredMessage("reward-lore").replace("{reward}", sReward) + " Bits");
        lore.add(Utils.getColoredMessage("chance-lore").replace("{chance}", String.valueOf(chance)));
        return lore;
    }

    public int getSlot() {
        return slot;
    }

    public ItemStack getItem() {
        return item;
    }

    public double getPrice() {
        return price;
    }

    public double getReward() {
        return reward;
    }

    public double getChance() {
        return chance;
    }

    public static List<Drink> getDrinks() {
        return drinks;
    }
}
