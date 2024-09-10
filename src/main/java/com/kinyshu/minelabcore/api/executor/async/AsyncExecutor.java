package com.kinyshu.minelabcore.api.executor.async;

import com.kinyshu.minelabcore.api.executor.abstracts.AbstractCodeExecutor;
import com.kinyshu.minelabcore.api.executor.async.timer.AsyncTimerExecutor;
import org.jetbrains.annotations.NotNull;

public class AsyncExecutor extends AbstractCodeExecutor {

    private AsyncTimerExecutor timerExecutor;

    public AsyncTimerExecutor getTimerExecutor() {
        return this.timerExecutor;
    }

    public void setTimerExecutor(AsyncTimerExecutor timerExecutor) {
        this.timerExecutor = timerExecutor;
    }

    @Override
    public AsyncTask execute(@NotNull Runnable runnable) {
        return new AsyncTask(Thread.startVirtualThread(runnable));
    }
}
