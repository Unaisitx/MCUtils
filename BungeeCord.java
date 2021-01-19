package com.zalyx.utils;

import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import lombok.Getter;
import com.zalyx.Hub;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BungeeCord implements PluginMessageListener {

    @Getter
    private static Map<String, Server> serversByName = new HashMap<>();
    @Getter
    private static List<Server> servers = new ArrayList<>();
    @Getter
    private static List<String> serversName = new ArrayList<>();

    @Getter
    private static JavaPlugin plugin = Hub.getInstance();
    @Getter
    private static int globalPlayers;

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();
        if (subchannel.equals("PlayerCount")) {
            try {
                String name = in.readUTF();
                int playercount = in.readInt();

                if (name.equalsIgnoreCase("ALL")) {
                    globalPlayers = playercount;
                    //Hub.getInstance().getLogger().info(globalPlayers + " in bungee.");
                } else if (serversName.contains(name)) {
                    Server server = serversByName.get(name);
                    if (server == null) server = new Server(name);

                    server.setPlayerCount(playercount);
                    //Hub.getInstance().getLogger().info(playercount + " in " + name);
                }
            } catch (Exception ignored) {}
        } else if (subchannel.equals("GetServers")) {
            String[] serverList = in.readUTF().split(", ");
            for (String serverName : serverList) {
                if (!serversName.contains(serverName)) {
                    new Server(serverName);
                    Hub.getInstance().getLogger().info("Adding " + serverName + " to the list.");
                }
            }
        }


    }

    public static void refreshGlobalCount() {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("PlayerCount");
        out.writeUTF("ALL");

        Player player = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);

        if (player != null) player.sendPluginMessage(Hub.getInstance(), "BungeeCord", out.toByteArray());
    }

    public static void refreshServerList() {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("GetServers");

        Player player = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);

        if (player != null) player.sendPluginMessage(Hub.getInstance(), "BungeeCord", out.toByteArray());
    }

    public static void sendToServer(Player p, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        try {
            out.writeUTF("Connect");
            out.writeUTF(server);
        } catch (Exception e) {
            System.out.println("Error while connecting to server. The error was: " + e.getMessage());
            e.printStackTrace();
        }
        p.sendPluginMessage(Hub.getInstance(), "BungeeCord", out.toByteArray());
    }

    public static void refreshServerCount() {
        for (Server server : servers) {
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("PlayerCount");
            out.writeUTF(server.getName());

            Player player = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);
            if (player != null) player.sendPluginMessage(Hub.getInstance(), "BungeeCord", out.toByteArray());
        }
    }
}