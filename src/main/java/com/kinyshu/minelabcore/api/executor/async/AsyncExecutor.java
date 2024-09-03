package com.kinyshu.minelabcore.api.executor.async;

import com.kinyshu.minelabcore.api.executor.abstracts.AbstractCodeExecutor;
import org.jetbrains.annotations.NotNull;

public class AsyncExecutor extends AbstractCodeExecutor {

    @Override
    public AsyncTask execute(@NotNull Runnable runnable) {
        return new AsyncTask(Thread.startVirtualThread(runnable));
    }
}
