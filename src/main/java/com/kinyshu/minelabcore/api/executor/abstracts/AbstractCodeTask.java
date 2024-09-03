package com.kinyshu.minelabcore.api.executor.abstracts;

public class AbstractCodeTask {

    private boolean completed;

    public AbstractCodeTask() {
        this.setCompleted(false);
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
