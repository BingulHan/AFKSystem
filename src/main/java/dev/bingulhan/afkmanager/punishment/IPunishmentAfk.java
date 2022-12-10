package dev.bingulhan.afkmanager.punishment;

import org.bukkit.entity.Player;

public interface IPunishmentAfk {

    void punishment(Player player);
    void punishment(Player player, int duration);


}
