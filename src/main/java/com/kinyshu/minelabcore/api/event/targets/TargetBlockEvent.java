package com.kinyshu.minelabcore.api.event.targets;

import com.kinyshu.minelabcore.api.event.handler.ExtendedEventHandler;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;

public class TargetBlockEvent extends ExtendedEventHandler {

    private Block block;

    public TargetBlockEvent(Block block) {
        this.setBlock(block);
    }

    public TargetBlockEvent(Block block, JavaPlugin javaPlugin) {
        super(javaPlugin);
        this.setBlock(block);
    }

    public Block getBlock() {
        return this.block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }
}
