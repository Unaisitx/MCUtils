package com.zalyx.utils;

import org.apache.commons.lang.StringEscapeUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemCreator {

    public static ItemStack create (Material m, int amount, short data, String name) {

        ItemStack item = new ItemStack(m, amount, (short) data);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(Color.translate(name));
        item.setItemMeta(itemMeta);

        return item;
    }

    public static ItemStack lore (Material m, int amount, short data, String name, List<String> lore) {

        ItemStack item = new ItemStack(m, amount, (short) data);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(Color.translate(name));
        itemMeta.setLore(color(lore));
        item.setItemMeta(itemMeta);

        return item;
    }

    private static List<String> color(List<String> text) {
        final List<String> messages = new ArrayList<String>();
        for (final String string : text) {
            messages.add(translateFromString(string));
        }
        return messages;
    }

    private static String translateFromString(String text) {
        return StringEscapeUtils.unescapeJava(ChatColor.translateAlternateColorCodes('&', text));
    }

}
