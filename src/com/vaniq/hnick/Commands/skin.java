package com.vaniq.hnick.Commands;

import com.vaniq.hnick.Events.Skin;
import com.vaniq.hnick.Main;
import com.vaniq.hnick.Utilities.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.plugin.Plugin;

public class skin implements CommandExecutor {

    public String getUUID(String name) {
        return Bukkit.getOfflinePlayer(name).getUniqueId().toString().replace("-", "");
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player p = (Player) sender;

        GameProfile gp = ((CraftPlayer) sender).getProfile();
        gp.getProperties().clear();
        Skin skin = new Skin("f7c77d999f154a66a87dc4a51ef30d19");

        if (skin.getSkinName() != null) {
            gp.getProperties().put(skin.getSkinName(), new Property(skin.getSkinName(), skin.getSkinValue(), skin.getSkinSignatur()));
        }

                for (Player pl : Bukkit.getOnlinePlayers()) {
                    pl.hidePlayer((Player) sender);
                }


                for (Player pl : Bukkit.getOnlinePlayers()) {
                    pl.showPlayer((Player) sender);
                }

        sender.sendMessage(ChatColor.GREEN + "Dein Skin ist nun: ");
        return false;
    }
}
