package com.kinyshu.minelabcore.api.executor.sync;

import com.kinyshu.minelabcore.api.core.MlcApi;
import com.kinyshu.minelabcore.api.executor.abstracts.AbstractCodeExecutor;
import com.kinyshu.minelabcore.api.executor.sync.timer.SyncTimerExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

/**
 * Синхронно исполняет код методами Bukkit
 */
public class SyncExecutor extends AbstractCodeExecutor {

    private SyncTimerExecutor timerExecutor;

    public SyncExecutor(JavaPlugin javaPlugin) {
        super(javaPlugin);
    }

    public SyncTimerExecutor getTimerExecutor() {
        return this.timerExecutor;
    }

    public void setTimerExecutor(SyncTimerExecutor timerExecutor) {
        this.timerExecutor = timerExecutor;
    }

    /**
     * Функция execute
     * Возвращает объект синхронной задачи
     * для манипулирования с ней в асинхронном потоке
     *
     * @param syncRunnable Объект оболочки Runnable
     * @return Объект SyncTask
     */
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

    /**
     * Функция wait
     * Используется для ожидания синхронного потока в асинхронном
     *
     * @param syncTask Объект синхронной задачи
     */
    public void wait(@NotNull SyncTask syncTask) {

        if (!Thread.currentThread().isVirtual()) {
            MlcApi.getApi().getLogger().log("Ошибка в выполнении функции wait, поток не является виртуальным.");
            return;
        }

        while (!syncTask.isCompleted()) {
            try {
                synchronized (Thread.currentThread()) {
                    Thread.currentThread().wait(1);
                }
                ;
            } catch (InterruptedException exception) {
                exception.printStackTrace();
                return;
            }
        }
    }
}
