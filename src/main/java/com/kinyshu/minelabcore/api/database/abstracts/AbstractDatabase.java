package com.kinyshu.minelabcore.api.database.abstracts;

import com.craftmend.storm.Storm;
import com.kinyshu.minelabcore.api.configuration.ConfigurationHandler;
import com.zaxxer.hikari.HikariConfig;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class AbstractDatabase {

    private JavaPlugin javaPlugin;

    private Storm storm;
    private HikariConfig hikariConfig;
    private ConfigurationHandler configurationHandler;

    public AbstractDatabase(JavaPlugin javaPlugin) {
        this.setJavaPlugin(javaPlugin);
        this.setHikariConfig(new HikariConfig());
    }

    public JavaPlugin getJavaPlugin() {
        return this.javaPlugin;
    }

    public void setJavaPlugin(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;
    }

    public Storm getStorm() {
        return this.storm;
    }

    public void setStorm(Storm storm) {
        this.storm = storm;
    }

    public HikariConfig getHikariConfig() {
        return this.hikariConfig;
    }

    public void setHikariConfig(HikariConfig hikariConfig) {
        this.hikariConfig = hikariConfig;
    }

    public ConfigurationHandler getConfigurationHandler() {
        return this.configurationHandler;
    }

    public void setConfigurationHandler(ConfigurationHandler configurationHandler) {
        this.configurationHandler = configurationHandler;
    }

    public abstract void parseConfiguration();
    public abstract boolean connect();
}
