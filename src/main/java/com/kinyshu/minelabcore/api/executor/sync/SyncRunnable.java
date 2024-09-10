package com.kinyshu.minelabcore.api.executor.sync;

/**
 * Оболочка интерфейса Runnable, требуется для
 * ожидания синхронного потока в асинхронном
 */
public class SyncRunnable implements Runnable {

    private SyncTask syncTask;

    @Override
    public void run() {

    }

    /**
     * Передаёт асинхронному потоку информацию
     * что поток завершён
     */
    public void exit() {
        this.getSyncTask().setCompleted(true);
    }

    public SyncTask getSyncTask() {
        return this.syncTask;
    }

    public void setSyncTask(SyncTask syncTask) {
        this.syncTask = syncTask;
    }
}
