package com.kinyshu.minelabcore.api.executor.sync.timer;

import com.kinyshu.minelabcore.api.executor.abstracts.AbstractCodeExecutor;
import com.kinyshu.minelabcore.api.executor.abstracts.AbstractCodeTask;
import com.kinyshu.minelabcore.api.executor.async.AsyncTask;
import com.kinyshu.minelabcore.api.executor.sync.SyncTask;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

public class SyncTimerExecutor extends AbstractCodeExecutor {

    public SyncTimerExecutor(JavaPlugin javaPlugin) {
        super(javaPlugin);
    }

    public BukkitTask executePostOnce(@NotNull Runnable runnable, long waitTime) {
        return this.getJavaPlugin().getServer().getScheduler().runTaskLater(this.getJavaPlugin(), runnable, waitTime * 20L);
    }

    public BukkitTask executeEndless(@NotNull Runnable runnable, long waitTime) {
        return this.getJavaPlugin().getServer().getScheduler().runTaskTimer(this.getJavaPlugin(), runnable, waitTime * 20L, waitTime * 20L);
    }

    @Override
    public SyncTask execute(@NotNull Runnable runnable) {
        return new SyncTask();
    }
}
