package com.vaniq.hnick.FileManager;

import com.vaniq.hnick.Main;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import java.io.File;
import java.io.IOException;

public class PlayerData
{
    public static YamlConfiguration getConfig() {
        File file = new File(Main.plugin.getDataFolder().getPath(), "PlayerData.yml");
        return YamlConfiguration.loadConfiguration(file);
    }


    private static void save(YamlConfiguration config) {
        File file = new File(Main.plugin.getDataFolder().getPath(), "PlayerData.yml");
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void edit(String path, Object value) {
        YamlConfiguration config = getConfig();
        config.set(path, value);
        save(config);
    }


    public static boolean playerExists(Player p) { return getConfig().contains(p.getUniqueId().toString()); }

    public static void setRank(Player p, String rank) { edit(String.valueOf(p.getUniqueId().toString()) + ".rank", rank); }

    public static void setRank(String uuid, String rank) { edit(String.valueOf(uuid) + ".rank", rank); }

    public static void setPrefix(Player p, String prefix) { edit(String.valueOf(p.getUniqueId().toString()) + ".prefix", prefix); }

    public static void setPrefix(String uuid, String prefix) { edit(String.valueOf(uuid) + ".prefix", prefix); }

    public static void setChat(Player p, String chat) { edit(String.valueOf(p.getUniqueId().toString()) + ".chatcolor", chat); }

    public static void setChat(String uuid, String chat) { edit(String.valueOf(uuid) + ".chatcolor", chat); }

    public static void setNameColor(Player p, String color) { edit(String.valueOf(p.getUniqueId().toString()) + ".namecolor", color); }

    public static void setNameColor(String uuid, String color) { edit(String.valueOf(uuid) + ".namecolor", color); }

    public static void setNick(Player p, String nick) { edit(String.valueOf(p.getUniqueId().toString()) + ".Nick", nick); }

    public static void setNick(String uuid, String nick) { edit(String.valueOf(uuid) + ".Nick", nick); }

    public static String getNick(Player p) { return getConfig().getString(String.valueOf(p.getUniqueId().toString()) + ".Nick"); }
    public static String getRank(Player p) { return getConfig().getString(String.valueOf(p.getUniqueId().toString()) + ".rank"); }
    public static String getPrefix(Player p) { return getConfig().getString(String.valueOf(p.getUniqueId().toString()) + ".prefix"); }
    public static String getChat(Player p) { return getConfig().getString(String.valueOf(p.getUniqueId().toString()) + ".chatcolor"); }
    public static String getNameColor(Player p) { return getConfig().getString(String.valueOf(p.getUniqueId().toString()) + ".namecolor"); }

    public static void tryInit(Player p) {
        if (!getConfig().contains(String.valueOf(p.getUniqueId().toString()) + ".Nick")) {
            setNick(p, null);
        }
        if (!getConfig().contains(String.valueOf(p.getUniqueId().toString()) + ".rank")) {
            setRank(p, null);
        }
        if (!getConfig().contains(String.valueOf(p.getUniqueId().toString()) + ".prefix")) {
            setPrefix(p, null);
        }
        if (!getConfig().contains(String.valueOf(p.getUniqueId().toString()) + ".chatcolor")) {
            setChat(p, "&7");
        }
        if (!getConfig().contains(String.valueOf(p.getUniqueId().toString()) + ".namecolor")) {
            setNameColor(p, "&7");
        }
    }

    public static void init(String uuid) {
        setNick(uuid, null);
        setRank(uuid, null);
        setPrefix(uuid, null);
        setChat(uuid, "&7");
        setNameColor(uuid, "&7");

    }

    public static String getUUIDByName(String player) {
        String toReturn = null;
        for (String uuid : getConfig().getKeys(false)) {
            if (getConfig().getString(String.valueOf(uuid) + ".Realname").equalsIgnoreCase(player)) {
                toReturn = uuid;
                break;
            }
        }
        return toReturn;
    }

    public static void mk() {
        File file = new File(Main.plugin.getDataFolder().getPath(), "PlayerData.yml");
        if (!file.exists())
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
