package com.kinyshu.minelabcore.api.executor.sync;

public class SyncRunnable implements Runnable {

    private SyncTask syncTask;

    @Override
    public void run() {

    }

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
