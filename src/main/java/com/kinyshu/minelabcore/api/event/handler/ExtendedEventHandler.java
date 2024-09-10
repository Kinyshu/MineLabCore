package com.kinyshu.minelabcore.api.event.handler;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
/**
 * Расширенный обработчик событий наследуется Listener
 */
public class ExtendedEventHandler implements Listener {

    // Указатель на объект JavaPlugin указанного плагина
    private JavaPlugin javaPlugin;

    public ExtendedEventHandler()
    {
    }

    public ExtendedEventHandler(JavaPlugin javaPlugin) {
        this.setJavaPlugin(javaPlugin);
    }

    public JavaPlugin getJavaPlugin() {
        return this.javaPlugin;
    }

    public void setJavaPlugin(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;
    }
}
