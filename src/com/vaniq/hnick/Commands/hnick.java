package com.vaniq.hnick.Commands;

import com.vaniq.hnick.Main;
import com.vaniq.hnick.Utilities.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class hnick implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player p = (Player)sender;

            if(p.hasPermission("hnick.admin") || p.isOp()) {
                if (args[0] == null) {

                    sender.sendMessage("§7----------[ §e§lHNick§7 ]----------");
                    sender.sendMessage("§aHNick Admin commands:");
                    sender.sendMessage("§d§l* §c/hnick reset (Player)");
                    return true;
                }
    }


        if(p.hasPermission("hnick.reload") || p.isOp()) {
                if (args[0].equalsIgnoreCase("reload")) {

                    Bukkit.getPluginManager().disablePlugin(Main.plugin);

                    Bukkit.getPluginManager().enablePlugin(Main.plugin);

                    p.sendMessage(ChatColor.GREEN + "HNick plugin has been reloaded.");
                }

            } else {
                p.sendMessage(ChatUtil.format(Main.config.getString("NoPerm")));
            }

        return false;
    }
}
