package com.kinyshu.minelabcore.api.plugins;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class MlcPluginsRegistrar {

    private Map<String, JavaPlugin> plugins;

    public MlcPluginsRegistrar() {
        this.setPlugins(new HashMap<>());
    }

    public Map<String, JavaPlugin> getPlugins() {
        return plugins;
    }

    private void setPlugins(Map<String, JavaPlugin> plugins) {
        this.plugins = plugins;
    }

    public JavaPlugin getPlugin(String pluginName) {
        return this.getPlugins().get(pluginName);
    }

    public void unregisterPlugin(String pluginName) {
        this.getPlugins().remove(pluginName);
    }

    public void registerPlugin(JavaPlugin javaPlugin) {
        this.getPlugins().put(javaPlugin.getName(), javaPlugin);
    }
}
