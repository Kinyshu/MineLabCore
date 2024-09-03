package com.kinyshu.minelabcore.api.executor.sync;

import com.kinyshu.minelabcore.api.executor.abstracts.AbstractCodeExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

public class SyncExecutor extends AbstractCodeExecutor {

    public SyncExecutor(JavaPlugin javaPlugin) {
        super(javaPlugin);
    }

    public SyncTask execute(@NotNull SyncRunnable syncRunnable) {
        SyncTask syncTask = new SyncTask();
        syncTask.setBukkitTask(this.run(syncRunnable));

        syncRunnable.setSyncTask(syncTask);

        return syncTask;
    }

    @Override
    public SyncTask execute(@NotNull Runnable runnable) {

        SyncTask syncTask = new SyncTask();
        syncTask.setBukkitTask(this.getJavaPlugin().getServer().getScheduler().runTask(this.getJavaPlugin(), runnable));

        return syncTask;
    }

    public BukkitTask run(@NotNull SyncRunnable syncRunnable) {
        return this.getJavaPlugin().getServer().getScheduler().runTask(this.getJavaPlugin(), syncRunnable);
    }

    public void wait(@NotNull SyncTask syncTask) {

        if (!Thread.currentThread().isVirtual()) {
            this.getJavaPlugin().getLogger().info("Ошибка в выполнении функции wait, поток не является виртуальным.");
            return;
        }

        while (!syncTask.isCompleted()) {
            try {
                synchronized (Thread.currentThread()) {
                    Thread.currentThread().wait(1);
                };
            }
            catch (InterruptedException exception) {
                exception.printStackTrace();
                return;
            }
        }
    }
}
