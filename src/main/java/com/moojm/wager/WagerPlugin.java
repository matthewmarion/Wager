package com.moojm.wager;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public class WagerPlugin extends JavaPlugin {

    private static WagerPlugin plugin;
    private static Economy econ = null;

    public void onEnable() {
        plugin = this;
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        if (!setupEconomy() ) {
            Bukkit.getLogger().log(Level.SEVERE, Utils.toColor("&cNo economy dependency found. Disabling."));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        Drink.loadDrinks();
        registerListeners();
        registerCommands();

    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new NPCListeners(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryListeners(), this);
    }

    private void registerCommands() {
        getCommand("wager").setExecutor(new CreateBarCommand());
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEconomy() {
        return econ;
    }


    public static WagerPlugin getPlugin() {
        return plugin;
    }

}
