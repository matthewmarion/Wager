package com.moojm.wager;

import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.rmi.CORBA.Util;

public class InventoryListeners implements Listener {

    @EventHandler
    public void on(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player == false) return;
        Inventory inv = event.getInventory();
        if (isBarInventory(inv) == false) return;

        ItemStack item = event.getCurrentItem();
        if (isValidClick(event, item) == false) return;

        Drink drink = Drink.getDrinkFromItemStack(item);
        if (drink == null) {
            event.setCancelled(true);
            return;
        }

        Player player = (Player) event.getWhoClicked();
        if (playerCanAfford(player, drink.getPrice()) == false) {
            event.setCancelled(true);
            player.sendMessage(Utils.getColoredMessage("cant-afford"));
            return;
        }

        boolean didWin = drink.roll();
        if (didWin == false) {
            event.setCancelled(true);
            player.closeInventory();
            withdraw(player, drink.getPrice());
            player.sendMessage(Utils.getColoredMessage("you-lost").replace("{price}", Utils.convertMoney(drink.getPrice())));
            return;
        }

        event.setCancelled(true);
        player.closeInventory();
        deposit(player, drink.getReward(), drink.getPrice());
        player.sendMessage(Utils.getColoredMessage("you-won").replace("{reward}", Utils.convertMoney(drink.getReward())));
    }

    private boolean playerCanAfford(Player player, double price) {
        double balance = WagerPlugin.getEconomy().getBalance(player);
        if (balance < price) {
            return false;
        }
        return true;
    }

    private boolean deposit(Player player, double reward, double price) {
        EconomyResponse response = WagerPlugin.getEconomy().depositPlayer(player, reward - price);
        if (response.transactionSuccess() == false) {
            player.sendMessage(Utils.getMessage("error"));
            player.sendMessage(Utils.toColor("&c" + response.errorMessage));
            return false;
        }
        return true;
    }

    private boolean withdraw(Player player, double price) {
        EconomyResponse response = WagerPlugin.getEconomy().withdrawPlayer(player, price);
        if (response.transactionSuccess() == false) {
            player.sendMessage(Utils.getMessage("error"));
            player.sendMessage(Utils.toColor("&c" + response.errorMessage));
            return false;
        }
        return true;
    }

    private boolean isValidClick(InventoryClickEvent event, ItemStack item) {
        if (isMovedOutInventory(event)) {
            event.setCancelled(true);
            return false;
        }

        if (item == null || item.getType() == Material.AIR) {
            event.setCancelled(true);
            return false;
        }
        return true;
    }

    private boolean isMovedOutInventory(InventoryClickEvent event) {
        return event.getRawSlot() >= event.getInventory().getSize();
    }

    private boolean isBarInventory(Inventory inv) {
        return inv.getName().equals(BarInventory.SHOP_NAME);
    }
}
