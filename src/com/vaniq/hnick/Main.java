package com.vaniq.hnick;

import com.vaniq.hnick.Commands.hnick;
import com.vaniq.hnick.Commands.nick;
import com.vaniq.hnick.Commands.nickrank;
import com.vaniq.hnick.Commands.skin;
import com.vaniq.hnick.Events.Chat;
import com.vaniq.hnick.Events.Join_Leave;
import com.vaniq.hnick.FileManager.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    public static FileConfiguration config;
    public static Main plugin;

    public void onEnable() {
        plugin = this;
        config = getConfig();

        saveDefaultConfig();

        getDataFolder().mkdir();
        getDataFolder().mkdirs();
        PlayerData.mk();

        getLogger().info("Plugin Enabled | Plugin Developed by SELA ");

        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new Chat(), this);
        Bukkit.getPluginManager().registerEvents(new Join_Leave(), this);

        getCommand("nick").setExecutor(new nick());
        getCommand("hnick").setExecutor(new hnick());
        getCommand("nickrank").setExecutor(new nickrank());

    }

}
