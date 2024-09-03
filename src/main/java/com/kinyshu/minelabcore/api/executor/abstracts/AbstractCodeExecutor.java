package com.kinyshu.minelabcore.api.executor.abstracts;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractCodeExecutor {

    private JavaPlugin javaPlugin;

    public AbstractCodeExecutor()
    {
    }

    public AbstractCodeExecutor(JavaPlugin javaPlugin) {
        this.setJavaPlugin(javaPlugin);
    }

    public JavaPlugin getJavaPlugin() {
        return this.javaPlugin;
    }

    public void setJavaPlugin(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;
    }

    public abstract AbstractCodeTask execute(@NotNull Runnable runnable);
}
