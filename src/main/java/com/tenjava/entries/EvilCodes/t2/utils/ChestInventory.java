package com.tenjava.entries.EvilCodes.t2.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ChestInventory {

    private Player player;
    private String world;
    private int x,y,z;
    private Inventory inventory;

    public ChestInventory(final Player player, final String world, final int x, final int y, final int z, final Inventory inventory) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.inventory = inventory;
    }

    public Location getLocation() {
        return new Location(Bukkit.getWorld(world), x, y, z);
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public Player getPlayer() {
        return this.player;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getZ() {
        return this.z;
    }
}
