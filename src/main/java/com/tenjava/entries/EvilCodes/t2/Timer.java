package com.tenjava.entries.EvilCodes.t2;

import com.tenjava.entries.EvilCodes.t2.listeners.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Timer {

    private static int task = 0;

    public static void start() {
        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(TenJava.instance, new Runnable() {
            @Override
            public void run() {
                for (final Player player : Bukkit.getOnlinePlayers()) {
                    final float exp = player.getExp();
                    if (exp < 0.1f) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 2));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 40, 1));
                        if (!PlayerListener.minusStr2.contains(player))
                            PlayerListener.minusStr2.add(player);
                    } else if (exp > 0.1f && exp < 0.3f) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 1));
                        if (!PlayerListener.minusStr1.contains(player))
                            PlayerListener.minusStr1.add(player);
                        if (PlayerListener.minusStr2.contains(player))
                            PlayerListener.minusStr2.remove(player);
                    } else if (exp > 0.3f && exp < 0.5f) {
                        if (PlayerListener.minusStr1.contains(player))
                            PlayerListener.minusStr1.remove(player);
                        if (PlayerListener.minusStr2.contains(player))
                            PlayerListener.minusStr2.remove(player);
                    } else if (exp > 0.5f && exp < 0.8f) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 1));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 40, 1));
                        if (!PlayerListener.minusStr1.contains(player))
                            PlayerListener.minusStr1.remove(player);
                        if (PlayerListener.minusStr2.contains(player))
                            PlayerListener.minusStr2.remove(player);
                    } else if (exp > 0.8f) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 2));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 40, 1));
                        if (PlayerListener.minusStr1.contains(player))
                            PlayerListener.minusStr1.remove(player);
                        if (PlayerListener.minusStr2.contains(player))
                            PlayerListener.minusStr2.remove(player);
                    }
                }
            }
        }, 20L, 20L);

    }

}
