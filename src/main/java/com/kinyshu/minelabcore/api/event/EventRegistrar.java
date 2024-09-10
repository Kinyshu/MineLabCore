package com.kinyshu.minelabcore.api.event;

import com.kinyshu.minelabcore.api.event.handler.ExtendedEventHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Регистрирует события, сохраняет их в список
 */
public class EventRegistrar {

    // Указатель на объект JavaPlugin указанного плагина
    private JavaPlugin javaPlugin;
    private List<ExtendedEventHandler> registeredEvents;

    public EventRegistrar(JavaPlugin javaPlugin) {
        this.setJavaPlugin(javaPlugin);
        this.setRegisteredEvents(new ArrayList<>());
    }

    public JavaPlugin getJavaPlugin() {
        return this.javaPlugin;
    }

    public void setJavaPlugin(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;
    }

    /**
     * Функция registerEvent
     * регистрирует события на сервере
     *
     * @param eventHandler объект обработчика событий
     */
    public void registerEvent(ExtendedEventHandler eventHandler) {

        if (eventHandler.getJavaPlugin() == null) {
            eventHandler.setJavaPlugin(this.getJavaPlugin());
        }

        this.getRegisteredEvents().add(eventHandler);
        this.getJavaPlugin().getServer().getPluginManager().registerEvents(eventHandler, eventHandler.getJavaPlugin());
    }

    public void registerEvents(List<ExtendedEventHandler> eventHandlers) {

        eventHandlers.forEach(eventHandler -> {
            if (eventHandler.getJavaPlugin() == null) {
                eventHandler.setJavaPlugin(this.getJavaPlugin());
            }

            this.getRegisteredEvents().add(eventHandler);
            this.getJavaPlugin().getServer().getPluginManager().registerEvents(eventHandler, eventHandler.getJavaPlugin());
        });
    }

    public List<ExtendedEventHandler> getRegisteredEvents() {
        return this.registeredEvents;
    }

    public void setRegisteredEvents(List<ExtendedEventHandler> registeredEvents) {
        this.registeredEvents = registeredEvents;
    }
}
