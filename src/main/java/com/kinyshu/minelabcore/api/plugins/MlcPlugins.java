package com.kinyshu.minelabcore.api.plugins;

import org.bukkit.plugin.java.JavaPlugin;

public class MlcPlugins {

    private MlcPluginsManager pluginsManager;
    private MlcPluginsRegistrar pluginsRegistrar;

    public MlcPlugins() {
        this.setPluginsManager(new MlcPluginsManager());
        this.setPluginsRegistrar(new MlcPluginsRegistrar());
    }

    public MlcPluginsManager getPluginsManager() {
        return pluginsManager;
    }

    private void setPluginsManager(MlcPluginsManager pluginsManager) {
        this.pluginsManager = pluginsManager;
    }

    public MlcPluginsRegistrar getPluginsRegistrar() {
        return pluginsRegistrar;
    }

    private void setPluginsRegistrar(MlcPluginsRegistrar pluginsRegistrar) {
        this.pluginsRegistrar = pluginsRegistrar;
    }
}
