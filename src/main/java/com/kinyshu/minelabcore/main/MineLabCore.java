package com.kinyshu.minelabcore.main;

import com.kinyshu.minelabcore.api.command.CommandRegistrar;
import com.kinyshu.minelabcore.api.core.MlcApi;
import com.kinyshu.minelabcore.api.messages.MessagesRegistrar;
import com.kinyshu.minelabcore.commands.plugin.MlcPluginCommand;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.StringUtil;

import java.util.Map;

public final class MineLabCore extends JavaPlugin {

    private MlcApi mlcApi;
    private CommandRegistrar commandRegistrar;

    @Override
    public void onLoad() {

        this.saveDefaultConfig();
        this.setMlcApi(new MlcApi(this));

        this.printAsciiInformation();
        this.enableRegisteredPlugins();
    }

    @Override
    public void onEnable() {
        this.deleteUnusedCommands();

        this.setCommandRegistrar(new CommandRegistrar(this));
        this.getCommandRegistrar().registerCommand(new MlcPluginCommand());
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
        this.disableRegisteredPlugins();
    }

    public MlcApi getMlcApi() {
        return this.mlcApi;
    }

    public void setMlcApi(MlcApi mlcApi) {
        this.mlcApi = mlcApi;
    }

    public CommandRegistrar getCommandRegistrar() {
        return this.commandRegistrar;
    }

    public void setCommandRegistrar(CommandRegistrar commandRegistrar) {
        this.commandRegistrar = commandRegistrar;
    }

    private void printAsciiInformation() {
        this.getMlcApi().getLogger()
                .log("")
                .log("§f███§c╗   §f███§c╗§f██§c╗§f███§c╗   §f██§c╗§f██§f██§f███§c╗§f██§c╗      §f██§f███§c╗ §f██§f██§f██§c╗  §f██§f██§f██§c╗ §f██§f██§f██§c╗ §f██§f██§f██§c╗ §f██§f██§f███§c╗")
                .log("§f██§f██§c╗ §f██§f██§c║§f██§c║§f██§f██§c╗  §f██§c║§f██§c╔§c═§c═§c═§c═╝§f██§c║     §f██§c╔§c═§c═§f██§c╗§f██§c╔§c═§c═§f██§c╗§f██§c╔§c═§c═§c═§c═╝§f██§c╔§c═§c═§c═§f██§c╗§f██§c╔§c═§c═§f██§c╗§f██§c╔§c═§c═§c═§c═╝")
                .log("§f██§c╔§f██§f██§c╔§f██§c║§f██§c║§f██§c╔§f██§c╗ §f██§c║§f██§f███§c╗  §f██§c║     §f██§f██§f███§c║§f██§f██§f██§c╔╝§f██§c║     §f██§c║   §f██§c║§f██§f██§f██§c╔╝§f██§f███§c╗  ")
                .log("§f██§c║§c╚§f██§c╔╝§f██§c║§f██§c║§f██§c║§c╚§f██§c╗§f██§c║§f██§c╔§c═§c═╝  §f██§c║     §f██§c╔§c═§c═§f██§c║§f██§c╔§c═§c═§f██§c╗§f██§c║     §f██§c║   §f██§c║§f██§c╔§c═§c═§f██§c╗§f██§c╔§c═§c═╝  ")
                .log("§f██§c║ §c╚§c═╝ §f██§c║§f██§c║§f██§c║ §c╚§f██§f██§c║§f██§f██§f███§c╗§f██§f██§f███§c╗§f██§c║  §f██§c║§f██§f██§f██§c╔╝§c╚§f██§f██§f██§c╗§c╚§f██§f██§f██§c╔╝§f██§c║  §f██§c║§f██§f██§f███§c╗")
                .log("§c╚§c═╝     §c╚§c═╝§c╚§c═╝§c╚§c═╝  §c╚§c═§c═§c═╝§c╚§c═§c═§c═§c═§c═§c═╝§c╚§c═§c═§c═§c═§c═§c═╝§c╚§c═╝  §c╚§c═╝§c╚§c═§c═§c═§c═§c═╝  §c╚§c═§c═§c═§c═§c═╝ §c╚§c═§c═§c═§c═§c═╝ §c╚§c═╝  §c╚§c═╝§c╚§c═§c═§c═§c═§c═§c═╝")
                .log("")
                .log("Загрузка плагина, версия " + this.getPluginMeta().getVersion())
                .log("Сайт: https://minelab.land/")
                .log("");
    }

    private void enableRegisteredPlugins() {
        var plugins = this.getMlcApi().getPlugins().getPluginsRegistrar().getPlugins();
        plugins.forEach((key, value) -> {

            if (key.contains(this.getName())) {
                return;
            }

            if (this.getMlcApi().getPlugins().getPluginsManager().enablePlugin(value)) {
                this.getMlcApi().getLogger().log("§7Включаю плагин " + key);
            }
        });
    }

    private void disableRegisteredPlugins() {
        var plugins = this.getMlcApi().getPlugins().getPluginsRegistrar().getPlugins();
        plugins.forEach((key, value) -> {
            if (this.getMlcApi().getPlugins().getPluginsManager().disablePlugin(value)) {
                this.getMlcApi().getLogger().log("§7Отключаю плагин " + key);
            }
        });
    }

    private void deleteUnusedCommands() {
        var knownCommands = Bukkit.getServer().getCommandMap().getKnownCommands();
        this.getConfig().getStringList("deleted-commands").forEach(command -> {
            var removedCommand = knownCommands.get(command);
            if (knownCommands.remove(command) != null) {
                MlcApi.getApi().getLogger().log("Удалил команду /" + command + "");
                MlcApi.getApi().getDeletedCommands().put(command, removedCommand);
            }
        });
    }
}
