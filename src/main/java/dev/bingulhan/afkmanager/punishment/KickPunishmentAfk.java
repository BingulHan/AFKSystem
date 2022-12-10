package dev.bingulhan.afkmanager.punishment;

import org.bukkit.entity.Player;

public class KickPunishmentAfk implements IPunishmentAfk{

    @Override
    public void punishment(Player player) {
        player.kickPlayer("AFK'sın");
    }

    @Override
    public void punishment(Player player, int duration) {
        if (duration >= 30) {
            player.kickPlayer("Uzun süredir AFK'sın");
        }
    }
}
