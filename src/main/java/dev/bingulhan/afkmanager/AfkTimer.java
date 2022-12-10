package dev.bingulhan.afkmanager;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Set;

public class AfkTimer extends BukkitRunnable {

    private AfkManager afkManager;
    public AfkTimer(AfkManager afkManager)  {

        this.afkManager = afkManager;

    }
    @Override
    public void run() {
        Set<OfflinePlayer> offlinePlayers = afkManager.getPlayerAfkRegisteryHashMap().keySet();

        for (OfflinePlayer offlinePlayer : offlinePlayers) {
            AfkRegistery registery = afkManager.getPlayerAfkRegisteryHashMap().get(offlinePlayer);

            if (registery.getMovementList().size()>afkManager.getAfkTimeout()) {

                if (registery.getMovementList().size()>0) {
                    boolean afkState = true;

                    for (int i = 1; i <= 15; i++) {
                        if (registery.getMovementList().get(registery.getMovementList().size()-i).equals(true)) {
                            afkState = false;
                        }
                    }

                    if (afkState && !registery.isAfk()) {
                            offlinePlayer.getPlayer().sendMessage(ChatColor.GOLD+"YOU ARE AFK NOW");
                            registery.setAfkDuration(0);

                    }

                    else if (!afkState && registery.isAfk()) {
                            offlinePlayer.getPlayer().sendMessage(ChatColor.GOLD+"YOU ARE NOT AFK NOW");
                            registery.setAfkDuration(0);

                    }

                    else if (afkState && registery.isAfk()) {
                            registery.setAfkDuration(registery.getAfkDuration()+1);

                            int seconds = registery.getAfkDuration();

                            long HH = seconds / 3600;
                            long MM = (seconds % 3600) / 60;
                            long SS = seconds % 60;
                            String timeInHHMMSS = String.format("%02d:%02d:%02d", HH, MM, SS);

                            afkManager.punishmentAfk.punishment(offlinePlayer.getPlayer(), registery.getAfkDuration());

                            offlinePlayer.getPlayer().sendTitle(ChatColor.AQUA+""+timeInHHMMSS,ChatColor.RED+"You Are AFK");
                    }
                    registery.setAfk(afkState);

                    if (registery.getMovementList().size()> afkManager.getAfkTimeout()+100){
                        for (int i = 0; i < afkManager.getAfkTimeout()+100; i++) {
                            registery.getMovementList().remove(registery.getMovementList().get(i));
                        }
                    }

                }

                registery.getMovementList().add(false);

            }
        }

    }
}
