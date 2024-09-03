package com.kinyshu.minelabcore.api.executor.sync;

import com.kinyshu.minelabcore.api.executor.abstracts.AbstractCodeTask;
import org.bukkit.scheduler.BukkitTask;

public class SyncTask extends AbstractCodeTask {

    private BukkitTask bukkitTask;

    public SyncTask()
    {
    }

    public SyncTask(BukkitTask bukkitTask) {
        this.setBukkitTask(bukkitTask);
    }

    public BukkitTask getBukkitTask() {
        return this.bukkitTask;
    }

    public void setBukkitTask(BukkitTask bukkitTask) {
        this.bukkitTask = bukkitTask;
    }
}