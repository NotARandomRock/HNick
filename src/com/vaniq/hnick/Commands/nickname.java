package com.vaniq.hnick.Commands;

import com.vaniq.hnick.FileManager.PlayerData;
import com.vaniq.hnick.Main;
import com.vaniq.hnick.Utilities.ChatUtil;
import io.netty.buffer.Unpooled;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutCustomPayload;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftMetaBook;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.List;

public class nickname implements CommandExecutor {

    public static String nick = null;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (!(sender instanceof Player)) {

            sender.sendMessage("Only players can execute this command.");
            return true;
        }

        Player p = (Player)sender;
        String namecolor = PlayerData.getNameColor(p);
        String prefix = PlayerData.getPrefix(p);

        ItemStack namebook = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta namemeta = (BookMeta) Bukkit.getItemFactory().getItemMeta(Material.WRITTEN_BOOK);



        try {
            if(cmd.getName().equalsIgnoreCase("nickname")) {
                if (p.hasPermission("hnick.nick") || p.isOp()) {
                     if(args.length == 1) {

                         List<IChatBaseComponent> namepages = (List<IChatBaseComponent>) CraftMetaBook.class.getDeclaredField("pages").get(namemeta);

                         TextComponent welname = new TextComponent(ChatUtil.format("Alright, now you'll\nneed to choose the\n&lNAME&r to use!"));
                         TextComponent random = new TextComponent(new ComponentBuilder("➤ Use a random name").event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/say hi")).create());
                         TextComponent custom = new TextComponent(new ComponentBuilder("➤ Enter a name").event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/say hi")).create());
                         TextComponent back = new TextComponent(ChatUtil.format("To go back to being\nyour usual self, type:\n&l/nick reset"));
                         IChatBaseComponent namepage = IChatBaseComponent.ChatSerializer.a(ComponentSerializer.toString(new BaseComponent[]{welname, random, custom, back}));

                         namemeta.setTitle("HNick");
                         namemeta.setAuthor("HNick");
                         namepages.add(namepage);

                         namebook.setItemMeta(namemeta);


                        return true;

                    } else

                    if (args[1].equalsIgnoreCase("Default")) {
                        PlayerData.tryInit(p);
                        PlayerData.edit(String.valueOf(p.getUniqueId().toString()) + ".Realname", p.getName());
                        p.setPlayerListName(ChatUtil.format(Main.config.getString("Ranks.Prefix.Default")) + " " + ChatUtil.format(PlayerData.getNameColor(p)) + nick);
                        p.setDisplayName(ChatUtil.format(Main.config.getString("Ranks.Prefix.Default")) + " " + ChatUtil.format(namecolor) + nick);
                        PlayerData.setNameColor(p, Main.config.getString("Ranks.NameColor.Default"));
                        PlayerData.setChat(p, Main.config.getString("Ranks.ChatColor.Default"));
                        PlayerData.setNick(p, nick);
                        PlayerData.setRank(p, "Default");
                        PlayerData.setPrefix(p, Main.config.getString("Ranks.Prefix.Default"));
                        openbook(namebook, p);
                        if(prefix != "none") {

                            p.setPlayerListName(ChatUtil.format(Main.config.getString("Ranks.Prefix.Default")) + " " + ChatUtil.format(PlayerData.getNameColor(p)) + nick);
                        } else {

                            p.setPlayerListName(ChatUtil.format(namecolor) + nick);
                        }

                    } else if (args[1].equalsIgnoreCase("Rank1")) {
                        PlayerData.tryInit(p);
                        PlayerData.edit(String.valueOf(p.getUniqueId().toString()) + ".Realname", p.getName());
                        p.setPlayerListName(ChatUtil.format(Main.config.getString("Ranks.Prefix.Rank1")) + " " + ChatUtil.format(PlayerData.getNameColor(p)) + nick);
                        p.setDisplayName(ChatUtil.format(Main.config.getString("Ranks.Prefix.Rank1")) + " " + ChatUtil.format(namecolor) + nick);
                        PlayerData.setNameColor(p, Main.config.getString("Ranks.NameColor.Rank1"));
                        PlayerData.setChat(p, Main.config.getString("Ranks.ChatColor.Rank1"));
                        PlayerData.setNick(p, nick);
                        PlayerData.setRank(p, "Rank1");
                        PlayerData.setPrefix(p, Main.config.getString("Ranks.Prefix.Rank1"));

                        if(prefix != "none") {

                            p.setPlayerListName(ChatUtil.format(Main.config.getString("Ranks.Prefix.Rank1")) + " " + ChatUtil.format(PlayerData.getNameColor(p)) + nick);
                        } else {
                            p.setPlayerListName(ChatUtil.format(namecolor) + nick);
                        }

                    } else if (args[1].equalsIgnoreCase("Rank2")) {
                        PlayerData.tryInit(p);
                        PlayerData.edit(String.valueOf(p.getUniqueId().toString()) + ".Realname", p.getName());
                        p.setPlayerListName(ChatUtil.format(Main.config.getString("Ranks.Prefix.Rank2")) + " " + ChatUtil.format(PlayerData.getNameColor(p)) + nick);
                        p.setDisplayName(ChatUtil.format(Main.config.getString("Ranks.Prefix.Rank2")) + " " + ChatUtil.format(namecolor) + nick);
                        PlayerData.setNameColor(p, Main.config.getString("Ranks.NameColor.Rank2"));
                        PlayerData.setChat(p, Main.config.getString("Ranks.ChatColor.Rank2"));
                        PlayerData.setNick(p, nick);
                        PlayerData.setRank(p, "Rank2");
                        PlayerData.setPrefix(p, Main.config.getString("Ranks.Prefix.Rank2"));

                        if(prefix != "none") {
                            p.setPlayerListName(ChatUtil.format(Main.config.getString("Ranks.Prefix.Rank2")) + " " + ChatUtil.format(PlayerData.getNameColor(p)) + nick);
                        } else {

                            p.setPlayerListName(ChatUtil.format(namecolor) + nick);
                        }

                    } else if (args[1].equalsIgnoreCase("Rank3")) {
                        PlayerData.tryInit(p);
                        PlayerData.edit(String.valueOf(p.getUniqueId().toString()) + ".Realname", p.getName());
                        p.setPlayerListName(ChatUtil.format(Main.config.getString("Ranks.Prefix.Rank3")) + " " + ChatUtil.format(PlayerData.getNameColor(p)) + nick);
                        p.setDisplayName(ChatUtil.format(Main.config.getString("Ranks.Prefix.Rank3")) + " " + ChatUtil.format(namecolor) + nick);
                        PlayerData.setNameColor(p, Main.config.getString("Ranks.NameColor.Rank3"));
                        PlayerData.setChat(p, Main.config.getString("Ranks.ChatColor.Rank3"));
                        PlayerData.setNick(p, nick);
                        PlayerData.setPrefix(p, Main.config.getString("Ranks.Prefix.Rank3"));
                        PlayerData.setRank(p, "Rank3");

                        if(prefix != "none") {

                            p.setPlayerListName(ChatUtil.format(Main.config.getString("Ranks.Prefix.Rank3")) + " " + ChatUtil.format(PlayerData.getNameColor(p)) + nick);
                        } else {
                            p.setPlayerListName(ChatUtil.format(namecolor) + nick);
                        }

                    } else if (args[1].equalsIgnoreCase("Rank4")) {
                        PlayerData.tryInit(p);
                        PlayerData.edit(String.valueOf(p.getUniqueId().toString()) + ".Realname",  p.getName());
                        p.setDisplayName(ChatUtil.format(Main.config.getString("Ranks.Prefix.Rank4")) + " " + ChatUtil.format(PlayerData.getNameColor(p)) + nick);
                        PlayerData.setNameColor(p, Main.config.getString("Ranks.NameColor.Rank4"));
                        PlayerData.setChat(p, Main.config.getString("Ranks.ChatColor.Rank4"));
                        PlayerData.setPrefix(p, Main.config.getString("Ranks.Prefix.Rank4"));
                        PlayerData.setNick(p, nick);
                        PlayerData.setRank(p, "Rank4");

                        if(prefix != "none") {
                            p.setPlayerListName(ChatUtil.format(Main.config.getString("Ranks.Prefix.Rank4")) + " " + ChatUtil.format(PlayerData.getNameColor(p)) + nick);
                        } else {
                            p.setPlayerListName(ChatUtil.format(namecolor) + nick);
                        }
                    }
                } else {
                    p.sendMessage(ChatUtil.format(Main.config.getString("NoPerm")));
                }
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void virtualbook(ItemStack book, Player p) {
        int slot = p.getInventory().getHeldItemSlot();
        ItemStack old = p.getInventory().getItem(slot);
        p.getInventory().setItem(slot, book);
        PacketPlayOutCustomPayload packet = new PacketPlayOutCustomPayload("MC|BOpen", new PacketDataSerializer(Unpooled.EMPTY_BUFFER));
        (((CraftPlayer)p).getHandle()).playerConnection.sendPacket(packet);
        p.getInventory().setItem(slot, old);
    }

    public static void openbook(ItemStack book, Player p) {
        int slot = p.getInventory().getHeldItemSlot();
        ItemStack old = p.getInventory().getItem(slot);
        p.getInventory().setItem(slot, book);
        PacketPlayOutCustomPayload packet = new PacketPlayOutCustomPayload("MC|BOpen", new PacketDataSerializer(Unpooled.EMPTY_BUFFER));
        (((CraftPlayer)p).getHandle()).playerConnection.sendPacket(packet);
        p.getInventory().setItem(slot, old);
    }

}
