package com.kinyshu.minelabcore.api.executor.async;

import com.kinyshu.minelabcore.api.executor.abstracts.AbstractCodeTask;

public class AsyncTask extends AbstractCodeTask {

    private Thread thread;

    public AsyncTask()
    {
    }

    public AsyncTask(Thread thread) {
        this.setThread(thread);
    }

    public Thread getThread() {
        return this.thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }
}
