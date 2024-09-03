package com.kinyshu.minelabcore.api.core;

import com.kinyshu.minelabcore.api.logger.MlcLogger;
import com.kinyshu.minelabcore.api.plugins.MlcPlugins;
import org.bukkit.command.Command;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class MlcApi {

    private JavaPlugin plugin;
    private MlcPlugins plugins;
    private MlcLogger logger;
    private Map<String, Command> deletedCommands;

    private static MlcApi api;

    public MlcApi(JavaPlugin plugin) {
        this.setPlugin(plugin);
        this.setPlugins(new MlcPlugins());
        this.setLogger(new MlcLogger(this.getPlugin()));
        this.registerPlugin(this.getPlugin());
        this.setDeletedCommands(new HashMap<>());

        this.registerApi();
    }

    public static MlcApi getApi() {
        return MlcApi.api;
    }

    private void registerApi() {
        MlcApi.api = this;
    }

    public MlcPlugins getPlugins() {
        return plugins;
    }

    private void setPlugins(MlcPlugins plugins) {
        this.plugins = plugins;
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    private void setPlugin(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public MlcLogger getLogger() {
        return logger;
    }

    private void setLogger(MlcLogger logger) {
        this.logger = logger;
    }

    public void registerPlugin(JavaPlugin plugin) {
        this.getPlugins().getPluginsRegistrar().registerPlugin(plugin);
    }

    public void unregisterPlugin(JavaPlugin plugin) {
        this.getPlugins().getPluginsRegistrar().unregisterPlugin(plugin.getName());
    }

    public Map<String, Command> getDeletedCommands() {
        return deletedCommands;
    }

    public void setDeletedCommands(Map<String, Command> deletedCommands) {
        this.deletedCommands = deletedCommands;
    }
}
