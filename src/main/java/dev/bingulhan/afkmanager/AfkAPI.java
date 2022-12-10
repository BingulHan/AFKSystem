package dev.bingulhan.afkmanager;

import org.bukkit.entity.Player;

public final class AfkAPI {

    private AfkManager afkManager;
    public AfkAPI(AfkManager afkManager) {
        this.afkManager = afkManager;
    }

    public boolean isPlayerAfk(Player player){
        return AfkManager.getInstance().getPlayerAfkRegisteryHashMap().get(player).isAfk();
    }

    public int getAfkDuration(Player player) {
        return AfkManager.getInstance().getPlayerAfkRegisteryHashMap().get(player).getAfkDuration();
    }

    public void setAfk(Player player, Boolean bool) {
        AfkManager.getInstance().getPlayerAfkRegisteryHashMap().get(player).getMovementList().clear();
        AfkManager.getInstance().getPlayerAfkRegisteryHashMap().get(player).setAfk(bool);
    }

    public int getAfkTimeout() {
        return afkManager.getAfkTimeout();
    }
}
