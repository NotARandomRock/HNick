package com.vaniq.hnick.Events;

import com.vaniq.hnick.FileManager.PlayerData;
import com.vaniq.hnick.Main;
import com.vaniq.hnick.Utilities.ChatUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Chat implements Listener {

    public static String nicked = null;
    public static String prefix = null;
    public static String namecolor = null;
    public static String chatcolor = null;
    public static String message = null;

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player p = event.getPlayer();

        prefix = PlayerData.getPrefix(p);
        nicked = PlayerData.getNick(p);
        namecolor = PlayerData.getNameColor(p);
        chatcolor = PlayerData.getChat(p);
        message = event.getMessage();

        if((nicked != null)) {

            if (!(prefix != "none")) {
                event.setFormat(ChatUtil.format(namecolor) + PlayerData.getNick(p) + ":" + ChatUtil.format(chatcolor) + " " + message);
            } else {
                event.setFormat(ChatUtil.format(prefix) + " " + ChatUtil.format(namecolor) + PlayerData.getNick(p) + ":" + ChatUtil.format(chatcolor) + " " + message);
            }
        }


    }
}
