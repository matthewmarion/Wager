package com.moojm.wager;

import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NPCListeners implements Listener {

    @EventHandler
    public void on(NPCRightClickEvent event) {
        Player player = event.getClicker();
        NPC npc = event.getNPC();
        String npcName = ChatColor.stripColor(npc.getName());
        String bartenderName = ChatColor.stripColor(Utils.getColoredMessage("bartender-name"));
        if ((npcName).equals(bartenderName) == false) {
            return;
        }
        BarInventory bar = new BarInventory();
        player.openInventory(bar.getInventory());
    }
}
