package com.kinyshu.minelabcore.commands.plugin;

import com.kinyshu.minelabcore.api.command.abstracts.AbstractCommand;
import com.kinyshu.minelabcore.commands.plugin.executor.MlcPluginCommandExecutor;

import java.util.List;

public class MlcPluginCommand extends AbstractCommand {

    public MlcPluginCommand() {

        this.setName("minelabcoreplugin");
        this.setDescription("Команда для управления плагинами");
        this.setUsage("/mlcp help");
        this.setAliases(List.of("mlcp", "mlcplugin", "plugin"));

        this.setExecutor(new MlcPluginCommandExecutor(this));
    }
}
