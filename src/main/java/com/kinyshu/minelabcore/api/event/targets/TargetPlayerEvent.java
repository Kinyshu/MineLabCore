package com.kinyshu.minelabcore.api.event.targets;

import com.kinyshu.minelabcore.api.event.handler.ExtendedEventHandler;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class TargetPlayerEvent extends ExtendedEventHandler {

    private Player player;

    public TargetPlayerEvent(Player player) {
        this.setPlayer(player);
    }

    public TargetPlayerEvent(Player player, JavaPlugin javaPlugin) {
        super(javaPlugin);
        this.setPlayer(player);
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
