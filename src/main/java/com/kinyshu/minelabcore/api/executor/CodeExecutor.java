package com.kinyshu.minelabcore.api.executor;

import com.kinyshu.minelabcore.api.executor.async.AsyncExecutor;
import com.kinyshu.minelabcore.api.executor.sync.SyncExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public class CodeExecutor {

    private SyncExecutor syncExecutor;
    private AsyncExecutor asyncExecutor;

    public CodeExecutor(JavaPlugin javaPlugin) {
        this.setSyncExecutor(new SyncExecutor(javaPlugin));
        this.setAsyncExecutor(new AsyncExecutor());
    }

    public SyncExecutor getSyncExecutor() {
        return this.syncExecutor;
    }

    private void setSyncExecutor(SyncExecutor syncExecutor) {
        this.syncExecutor = syncExecutor;
    }

    public AsyncExecutor getAsyncExecutor() {
        return this.asyncExecutor;
    }

    private void setAsyncExecutor(AsyncExecutor asyncExecutor) {
        this.asyncExecutor = asyncExecutor;
    }
}
