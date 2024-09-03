package com.kinyshu.minelabcore.main;

import com.kinyshu.minelabcore.api.command.CommandRegistrar;
import com.kinyshu.minelabcore.api.core.MlcApi;
import com.kinyshu.minelabcore.api.event.EventRegistrar;
import com.kinyshu.minelabcore.api.event.targets.TargetPlayerEvent;
import com.kinyshu.minelabcore.commands.plugin.MlcPluginCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class MineLabCore extends JavaPlugin {

    private MlcApi mlcApi;
    private CommandRegistrar commandRegistrar;

    @Override
    public void onLoad() {

        this.saveDefaultConfig();
        this.setMlcApi(new MlcApi(this));

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

    @Override
    public void onEnable() {
        this.setCommandRegistrar(new CommandRegistrar(this));
        this.getCommandRegistrar().registerCommand(new MlcPluginCommand());

        var knownCommands = Bukkit.getServer().getCommandMap().getKnownCommands();
        this.getConfig().getStringList("deleted-commands").forEach(command -> {
            var removedCommand = knownCommands.get(command);
            if (knownCommands.remove(command) != null) {
                MlcApi.getApi().getLogger().log("Удалил команду /" + command + "");
                MlcApi.getApi().getDeletedCommands().put(command, removedCommand);
            }
        });
    }

    @Override
    public void onDisable() {
        var plugins = this.getMlcApi().getPlugins().getPluginsRegistrar().getPlugins();
        plugins.forEach((key, value) -> {
            if (this.getMlcApi().getPlugins().getPluginsManager().disablePlugin(value)) {
                this.getMlcApi().getLogger().log("§7Отключаю плагин " + key);
            }
        });
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
}
