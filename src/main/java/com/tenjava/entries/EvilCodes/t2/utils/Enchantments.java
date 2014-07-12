package com.tenjava.entries.EvilCodes.t2.utils;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Enchantments {

    public static final Material[] SWORDS = { Material.WOOD_SWORD, Material.STONE_SWORD, Material.IRON_SWORD, Material.GOLD_SWORD, Material.DIAMOND_SWORD };
    public static final Material[] ARMOR  = { Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.CHAINMAIL_HELMET, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_BOOTS, Material.GOLD_HELMET, Material.GOLD_CHESTPLATE, Material.GOLD_LEGGINGS, Material.GOLD_BOOTS, Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS };

    /**
     * This only enchants an item if the player is holding one which is listed in the arrays above
     * @param user The player
     * @param itemStack The item to enchant
     * @return not interesting boolean
     */
    public static boolean enchant(final Player user, final ItemStack itemStack) {

        final Random typeRandom = new Random();
        final Random levelRandom = new Random();

        for (final Material material : SWORDS) {
            if (material.equals(itemStack.getType())) {
                final int type = typeRandom.nextInt(3);

                if (type == 0) {
                    itemStack.addEnchantment(Enchantment.DAMAGE_UNDEAD, levelRandom.nextInt(2) + 1);
                    itemStack.addEnchantment(Enchantment.FIRE_ASPECT, levelRandom.nextInt(2) + 1);
                } else if (type == 1) {
                    itemStack.addEnchantment(Enchantment.DAMAGE_ALL, levelRandom.nextInt(2) + 1);
                    itemStack.addEnchantment(Enchantment.KNOCKBACK, levelRandom.nextInt(2) + 1);
                } else if (type == 2) {
                    itemStack.addEnchantment(Enchantment.DURABILITY, levelRandom.nextInt(2) + 1);
                    itemStack.addEnchantment(Enchantment.FIRE_ASPECT, levelRandom.nextInt(2) + 1);
                }
                return true;
            }
        }

        for (final Material material : ARMOR) {
            if (material.equals(itemStack.getType())) {
                final int type = typeRandom.nextInt(3);

                if (type == 0) {
                    itemStack.addEnchantment(Enchantment.PROTECTION_PROJECTILE, levelRandom.nextInt(2) + 1);
                    itemStack.addEnchantment(Enchantment.PROTECTION_FIRE, levelRandom.nextInt(2) + 1);
                } else if (type == 1) {
                    itemStack.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, levelRandom.nextInt(2) + 1);
                    itemStack.addEnchantment(Enchantment.PROTECTION_FIRE, levelRandom.nextInt(2) + 1);
                } else if (type == 2) {
                    itemStack.addEnchantment(Enchantment.PROTECTION_PROJECTILE, levelRandom.nextInt(2) + 1);
                    itemStack.addEnchantment(Enchantment.THORNS, levelRandom.nextInt(2) + 1);
                }
                return true;
            }
        }

        if (itemStack.getType() == Material.BOW) {
            final int type = typeRandom.nextInt(3);

            if (type == 0) {
                itemStack.addEnchantment(Enchantment.ARROW_DAMAGE, levelRandom.nextInt(2) + 1);
                itemStack.addEnchantment(Enchantment.ARROW_FIRE, 1);
            } else if (type == 1) {
                itemStack.addEnchantment(Enchantment.ARROW_DAMAGE, levelRandom.nextInt(2) + 1);
                itemStack.addEnchantment(Enchantment.ARROW_INFINITE, 1);
            } else if (type == 2) {
                itemStack.addEnchantment(Enchantment.ARROW_DAMAGE, levelRandom.nextInt(2) + 1);
                itemStack.addEnchantment(Enchantment.ARROW_KNOCKBACK, levelRandom.nextInt(2) + 1);
            }
            return true;
        }

        return false;
    }

}
