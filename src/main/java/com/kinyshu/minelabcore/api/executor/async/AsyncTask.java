package com.kinyshu.minelabcore.api.executor.async;

import com.kinyshu.minelabcore.api.executor.abstracts.AbstractCodeTask;

public class AsyncTask extends AbstractCodeTask {

    private Thread thread;
    private Object object;

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

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
