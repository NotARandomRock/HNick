package com.vaniq.hnick.Events;

import com.vaniq.hnick.FileManager.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Join_Leave implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player p = event.getPlayer();

        p.setPlayerListName(p.getName());
        p.setDisplayName(p.getName());
        PlayerData.setNick(p, null);
        PlayerData.setNameColor(p, null);
        PlayerData.setChat(p, null);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {

        Player p = event.getPlayer();

        p.setPlayerListName(p.getName());
        p.setDisplayName(p.getName());
        PlayerData.setNick(p, null);
        PlayerData.setNameColor(p, null);
        PlayerData.setChat(p, null);
    }
}
