package com.zalyx.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationSerializer {

    public static Location location(int x, int y, int z, int pitch, int yatch, String world) {
        Location loc = new Location(Bukkit.getWorld(world), x, y, z, pitch, yatch);
        return loc;
    }

    public static String serializeLocation(Location location) {
        return location.getWorld().getName() + ":" + location.getX() + ":" + location.getY() + ":" + location.getZ() + ":" + location.getYaw() + ":" + location.getPitch();
    }

    public static Location deserializeLocation(String serializedLocation) {
        String[] split = serializedLocation.split(":");
        return new Location(Bukkit.getServer().getWorld(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]), Double.parseDouble(split[3]), Float.parseFloat(split[4]), Float.parseFloat(split[5]));
    }
}