package com.kinyshu.minelabcore.api.logger;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;


public class MlcLogger {

    private JavaPlugin javaPlugin;
    private ConsoleCommandSender commandSender;

    public MlcLogger(JavaPlugin javaPlugin) {
        this.setJavaPlugin(javaPlugin);
        this.setCommandSender(this.getJavaPlugin().getServer().getConsoleSender());
    }

    private JavaPlugin getJavaPlugin() {
        return this.javaPlugin;
    }

    private void setJavaPlugin(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;
    }

    private ConsoleCommandSender getCommandSender() {
        return this.commandSender;
    }

    private void setCommandSender(ConsoleCommandSender commandSender) {
        this.commandSender = commandSender;
    }

    public MlcLogger log(String message) {
        this.getCommandSender().sendMessage(String.format("[%s] %s", this.getJavaPlugin().getName(), message));
        return this;
    }
}
