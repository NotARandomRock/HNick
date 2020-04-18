package com.vaniq.hnick.Commands;

import com.mojang.authlib.GameProfile;
import com.vaniq.hnick.Events.Chat;
import com.vaniq.hnick.Events.Skin;
import com.vaniq.hnick.FileManager.PlayerData;
import com.vaniq.hnick.Main;
import com.vaniq.hnick.Utilities.ChatUtil;

import io.netty.buffer.Unpooled;
import net.md_5.bungee.api.chat.*;

import net.md_5.bungee.chat.ComponentSerializer;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutCustomPayload;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftMetaBook;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import com.mojang.authlib.properties.Property;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class nick implements CommandExecutor {

    public static String getUUID(String name){
        return Bukkit.getOfflinePlayer(name).getUniqueId().toString().replace("-", "");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (!(sender instanceof Player)) {

            sender.sendMessage("Only players can execute this command.");
            return true;
        }

        Player p = (Player)sender;
        String namecolor = PlayerData.getNameColor(p);
        String prefix = PlayerData.getPrefix(p);

        try {

        if (p.hasPermission("hnick.nick") || p.isOp()) {
            if(args.length == 0) {

                ItemStack nickbook = new ItemStack(Material.WRITTEN_BOOK);
                BookMeta meta = (BookMeta) Bukkit.getItemFactory().getItemMeta(Material.WRITTEN_BOOK);

                List<IChatBaseComponent> pages = (List<IChatBaseComponent>) CraftMetaBook.class.getDeclaredField("pages").get(meta);

                TextComponent welcome = new TextComponent(ChatUtil.format("Nicknames allow you to\nplay with a different\nusername to not get\nrecognized.\n\nAll rules still apply. You can still be reported and all name history is stored.\n\n"));
                TextComponent setup = new TextComponent(new ComponentBuilder("➤ I understand, setup my nickname").event(new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/nickrank " + p.getName())).create());
                IChatBaseComponent page = IChatBaseComponent.ChatSerializer.a(ComponentSerializer.toString(new BaseComponent[]{welcome, setup}));

                meta.setTitle("HNick");
                meta.setAuthor("HNick");
                pages.add(page);

                nickbook.setItemMeta(meta);

                virtualbook(nickbook, p);



            } else  if (args[0].equalsIgnoreCase("reset")) {
                p.setPlayerListName(p.getName());
                p.setDisplayName(p.getName());
                p.sendMessage(ChatColor.GREEN + "Your nick has been reset.");
                PlayerData.setNick(p, null);
                PlayerData.setNameColor(p, null);
                PlayerData.setChat(p, null);

            } else if (args[0].equalsIgnoreCase(args[0])) {

                nickrank.nick = args[0];

                ItemStack rankbook = new ItemStack(Material.WRITTEN_BOOK);
                BookMeta meta = (BookMeta) Bukkit.getItemFactory().getItemMeta(Material.WRITTEN_BOOK);

                List<IChatBaseComponent> rankpages = (List<IChatBaseComponent>) CraftMetaBook.class.getDeclaredField("pages").get(meta);


                TextComponent welrank = new TextComponent(ChatUtil.format("Let's get you set up\nwith your nickname!\nFirst, you'll need to choose which &lRANK\nyou would like to be shown as when nicked.\n\n"));
                TextComponent member = new TextComponent(new ComponentBuilder(ChatUtil.format((Main.config.getString("Ranks.Book-Display.Default"))) + "\n").event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/nickrank " + p.getName() + " default")).create());
                TextComponent rank1 = new TextComponent(new ComponentBuilder(ChatUtil.format((Main.config.getString("Ranks.Book-Display.Rank1"))) + "\n").event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/nickrank " + p.getName() + " Rank1")).create());
                TextComponent rank2 = new TextComponent(new ComponentBuilder(ChatUtil.format((Main.config.getString("Ranks.Book-Display.Rank2"))) + "\n").event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/nickrank " + p.getName() + " Rank2")).create());
                TextComponent rank3 = new TextComponent(new ComponentBuilder(ChatUtil.format((Main.config.getString("Ranks.Book-Display.Rank3"))) + "\n").event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/nickrank " + p.getName() + " Rank3")).create());
                TextComponent rank4 = new TextComponent(new ComponentBuilder(ChatUtil.format((Main.config.getString("Ranks.Book-Display.Rank4"))) + "\n").event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/nickrank " + p.getName() + " Rank4")).create());
                IChatBaseComponent rankpage = IChatBaseComponent.ChatSerializer.a(ComponentSerializer.toString(new BaseComponent[]{welrank, member, rank1, rank2, rank3, rank4}));

                meta.setTitle("HNick");
                meta.setAuthor("HNick");
                rankpages.add(rankpage);

                rankbook.setItemMeta(meta);

                PlayerData.tryInit(p);
                PlayerData.edit(String.valueOf(p.getUniqueId().toString()) + ".Realname", p.getName());

                nickrank.virtualbook(rankbook, p);


            }


        } else {
            p.sendMessage(ChatUtil.format(Main.config.getString("NoPerm")));
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


}
