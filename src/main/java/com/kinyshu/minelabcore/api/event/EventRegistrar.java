package com.kinyshu.minelabcore.api.event;

import com.kinyshu.minelabcore.api.event.handler.ExtendedEventHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class EventRegistrar {

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

    public void registerEvent(ExtendedEventHandler abstractEventHandler) {

        if (abstractEventHandler.getJavaPlugin() == null) {
            abstractEventHandler.setJavaPlugin(this.getJavaPlugin());
        }

        this.getRegisteredEvents().add(abstractEventHandler);
        this.getJavaPlugin().getServer().getPluginManager().registerEvents(abstractEventHandler, abstractEventHandler.getJavaPlugin());
    }

    public List<ExtendedEventHandler> getRegisteredEvents() {
        return this.registeredEvents;
    }

    public void setRegisteredEvents(List<ExtendedEventHandler> registeredEvents) {
        this.registeredEvents = registeredEvents;
    }
}
