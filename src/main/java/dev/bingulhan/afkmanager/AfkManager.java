package dev.bingulhan.afkmanager;

import dev.bingulhan.afkmanager.listener.AfkListener;
import dev.bingulhan.afkmanager.punishment.IPunishmentAfk;
import dev.bingulhan.afkmanager.punishment.KickPunishmentAfk;
import lombok.Getter;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class AfkManager extends JavaPlugin {


    @Getter
    private HashMap<OfflinePlayer, AfkRegistery> playerAfkRegisteryHashMap;

    @Getter
    private static AfkManager instance;

    @Getter
    private int afkTimeout = 15;

    @Getter
    private AfkAPI api;

    @Getter
    private boolean testPunishment = false;

    @Getter
    IPunishmentAfk punishmentAfk;
    @Override
    public void onEnable() {

        instance = this;

        getConfig().options().copyDefaults(true);
        saveConfig();

        testPunishment = getConfig().getBoolean("test-punishment");

        afkTimeout = getConfig().getInt("afk-timeout");

        playerAfkRegisteryHashMap = new HashMap<>();

        getServer().getPluginManager().registerEvents(new AfkListener(), this);

        new AfkTimer(this).runTaskTimer(this, 0L, 20L);

        for (Player onlinePlayer : getServer().getOnlinePlayers()) {
            playerAfkRegisteryHashMap.put(onlinePlayer, new AfkRegistery());

        }

        api = new AfkAPI(this);


        punishmentAfk = new KickPunishmentAfk();

    }

    @Override
    public void onDisable() {





    }

}
