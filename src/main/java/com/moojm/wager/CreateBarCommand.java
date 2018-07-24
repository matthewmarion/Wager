package com.moojm.wager;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class CreateBarCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("wager.admin")) {
            return true;
        }
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        if (!args[0].equalsIgnoreCase("create")) {
            return false;
        }
        Location location = player.getLocation();
        createBartender(location);
        return false;
    }

    private void createBartender(Location location) {
        NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, Utils.getColoredMessage("bartender-name"));
        npc.spawn(location);
    }
}
