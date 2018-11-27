package com.moojm.wager;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.text.DecimalFormat;

public final class Utils {

    private Utils() {}

    public static String toColor(String context) {
        return ChatColor.translateAlternateColorCodes('&', context);
    }

    public static String getMessage(String node) {
        return Utils.getConfig().getString(node);
    }

    public static String getColoredMessage(String node) {
        return Utils.toColor(Utils.getMessage(node));
    }

    public static FileConfiguration getConfig() {
        return WagerPlugin.getPlugin().getConfig();
    }

    public static String convertMoney(double amt){
        DecimalFormat df = new DecimalFormat("#.###");
        String s;
        Double thou = new Double("1000");
        Double mill = new Double("1000000");
        Double bill = new Double("1000000000");
        Double tril = new Double("1000000000000");
        Double quad = new Double("1000000000000000");
        Double quin = new Double("1000000000000000000");
        if(amt>=quin){
            amt = amt/quin;
            s = df.format(amt) + "quin";
        }
        else if(amt>=quad){
            amt = amt/quad;
            s = df.format(amt) + "quad";
        }
        else if(amt>=tril){
            amt = amt/tril;
            s = df.format(amt) + "tril";
        }
        else if(amt>=bill){
            amt = amt/bill;
            s = df.format(amt) + "bil";
        }
        else if(amt>=mill){
            amt = amt/mill;
            s = df.format(amt) + "mil";
        }
        else if(amt>=thou){
            amt = amt/thou;
            s = df.format(amt) + "k";
        }
        else{
            s = df.format(amt) + "";
        }
        return s;
    }
}
