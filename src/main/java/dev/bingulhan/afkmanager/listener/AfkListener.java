package dev.bingulhan.afkmanager.listener;

import dev.bingulhan.afkmanager.AfkManager;
import dev.bingulhan.afkmanager.AfkRegistery;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class AfkListener implements Listener {

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event) {
        if (!AfkManager.getInstance().getPlayerAfkRegisteryHashMap().keySet().stream().anyMatch(of -> of.getName().equals(event.getPlayer().getName()))) {
            AfkManager.getInstance().getPlayerAfkRegisteryHashMap().put(event.getPlayer(), new AfkRegistery());
        }
    }

    @EventHandler
    public void onQuitEvent(PlayerQuitEvent event) {
        if (AfkManager.getInstance().getPlayerAfkRegisteryHashMap().keySet().stream().anyMatch(of -> of.getName().equals(event.getPlayer().getName()))) {
            AfkManager.getInstance().getPlayerAfkRegisteryHashMap().remove(AfkManager.getInstance().getPlayerAfkRegisteryHashMap().get(event.getPlayer()));
        }
    }


    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
        if (AfkManager.getInstance().getPlayerAfkRegisteryHashMap().keySet().stream().anyMatch(of -> of.getName().equals(event.getPlayer().getName()))) {
            AfkManager.getInstance().getPlayerAfkRegisteryHashMap().get(event.getPlayer()).getMovementList().add(true);
        }
    }

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event) {
        if (AfkManager.getInstance().getPlayerAfkRegisteryHashMap().keySet().stream().anyMatch(of -> of.getName().equals(event.getPlayer().getName()))) {
            AfkManager.getInstance().getPlayerAfkRegisteryHashMap().get(event.getPlayer()).getMovementList().add(true);
        }
    }

    @EventHandler
    public void onMoveEvent(PlayerMoveEvent event) {
        if (event.getTo().getBlockX() != event.getFrom().getBlockX() || event.getTo().getBlockZ() != event.getFrom().getBlockZ()) {
            if (AfkManager.getInstance().getPlayerAfkRegisteryHashMap().keySet().stream().anyMatch(of -> of.getName().equals(event.getPlayer().getName()))) {
                AfkManager.getInstance().getPlayerAfkRegisteryHashMap().get(event.getPlayer()).getMovementList().add(true);
            }
        }
    }

}
