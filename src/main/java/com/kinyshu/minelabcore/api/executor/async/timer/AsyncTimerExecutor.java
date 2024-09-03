package com.kinyshu.minelabcore.api.executor.async.timer;

import com.kinyshu.minelabcore.api.executor.abstracts.AbstractCodeExecutor;
import com.kinyshu.minelabcore.api.executor.abstracts.AbstractCodeTask;
import com.kinyshu.minelabcore.api.executor.async.AsyncTask;
import org.jetbrains.annotations.NotNull;

public class AsyncTimerExecutor extends AbstractCodeExecutor {
    public AsyncTask executePostOnce(@NotNull Runnable runnable, long waitTime) {
        return this.execute(() -> {

            try {
                synchronized (Thread.currentThread()) {
                    Thread.currentThread().wait(waitTime);
                };
            }
            catch (InterruptedException exception) {
                exception.printStackTrace();
            }

            runnable.run();
            Thread.currentThread().interrupt();
        });
    }

    public AsyncTask executeEndless(@NotNull Runnable runnable, long waitTime) {
        return this.execute(() -> {

            runnable.run();

            try {
                synchronized (Thread.currentThread()) {
                    Thread.currentThread().wait(waitTime);
                };
            }
            catch (InterruptedException exception) {
                exception.printStackTrace();
                Thread.currentThread().interrupt();
            }
        });
    }

    @Override
    public AsyncTask execute(@NotNull Runnable runnable) {
        return new AsyncTask(Thread.startVirtualThread(runnable));
    }
}
