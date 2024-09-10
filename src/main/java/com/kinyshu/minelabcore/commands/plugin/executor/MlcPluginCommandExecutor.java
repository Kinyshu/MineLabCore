package com.kinyshu.minelabcore.commands.plugin.executor;

import com.kinyshu.minelabcore.api.command.abstracts.AbstractCommandExecutor;
import com.kinyshu.minelabcore.api.command.argument.ExecuteArgument;
import com.kinyshu.minelabcore.api.command.argument.TabCompleteArgument;
import com.kinyshu.minelabcore.api.core.MlcApi;
import com.kinyshu.minelabcore.api.executor.CodeExecutor;
import com.kinyshu.minelabcore.commands.plugin.MlcPluginCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class MlcPluginCommandExecutor extends AbstractCommandExecutor {

    private CodeExecutor executor;

    public MlcPluginCommandExecutor(MlcPluginCommand pluginCommand) {
        super(pluginCommand);
        this.setExecutor(new CodeExecutor(this.getCommand().getJavaPlugin()));
    }

    @Override
    public boolean onCommandExecuted(ExecuteArgument commandArgument) {

        this.getExecutor().getAsyncExecutor().execute(() -> {

            CommandSender sender = commandArgument.getSender();
            String[] arguments = commandArgument.getArguments();

            if (sender.isOp() || sender.hasPermission("mlc.core")) {

                if (arguments.length == 0) {
                    arguments = new String[]{"help"};
                }

                switch (arguments[0].toLowerCase()) {

                    case "load": {
                        if (arguments.length == 2) {
                            boolean status = MlcApi.getApi().getPlugins().getPluginsManager().loadPlugin(arguments[1] + ".jar");
                            sender.sendMessage("§f[§aИнформация§f] §7Статус загрузки плагина " + arguments[1] + ": §9" + Boolean.toString(status));
                        }
                    }
                    break;

                    case "unload": {
                        if (arguments.length == 2) {
                            Plugin plugin = this.getPlugin(arguments[1], sender);
                            if (plugin == null) {
                                break;
                            }

                            boolean status = MlcApi.getApi().getPlugins().getPluginsManager().unloadPlugin(plugin);
                            sender.sendMessage("§f[§aИнформация§f] §7Статус выгрузки плагина: §9" + Boolean.toString(status));
                        }
                    }
                    break;

                    case "reload": {
                        if (arguments.length == 2) {
                            Plugin plugin = Bukkit.getPluginManager().getPlugin(arguments[1]);
                            if (plugin == null) {
                                sender.sendMessage("§f[§cОшибка§f] §7Плагина " + arguments[1] + " не существует");
                                break;
                            }

                            if (MlcApi.getApi().getPlugins().getPluginsManager().disablePlugin(plugin)
                                    && MlcApi.getApi().getPlugins().getPluginsManager().enablePlugin(plugin)) {
                                sender.sendMessage("§f[§aИнформация§f] §7Плагин успешно перезапущен");
                            } else {
                                sender.sendMessage("§f[§cОшибка§f] §7Плагин не перезапущен");
                            }
                        }
                    }
                    break;

                    case "enable": {
                        if (arguments.length == 2) {

                            Plugin plugin = this.getPlugin(arguments[1], sender);
                            if (plugin == null) {
                                break;
                            }

                            boolean status = MlcApi.getApi().getPlugins().getPluginsManager().enablePlugin(plugin);
                            sender.sendMessage("§f[§aИнформация§f] §7Статус включения плагина: §9" + status);
                        }
                    }
                    break;

                    case "disable": {
                        if (arguments.length == 2) {
                            Plugin plugin = this.getPlugin(arguments[1], sender);
                            if (plugin == null) {
                                break;
                            }

                            boolean status = MlcApi.getApi().getPlugins().getPluginsManager().disablePlugin(plugin);
                            sender.sendMessage("§f[§aИнформация§f] §7Статус выключения плагина: §9" + status);
                        }
                    }
                    break;

                    case "list": {

                        if (arguments.length == 1) {
                            var plugins = MlcApi.getApi().getPlugins().getPluginsRegistrar().getPlugins();
                            sender.sendMessage("§f[§aИнформация§f] §7Список загруженных плагинов MineLabCore:");
                            plugins.forEach((key, value) -> {
                                String isEnabled = value.isEnabled() ? "§a" : "§c";
                                sender.sendMessage("§f[§aИнформация§f] §7Плагин: " + isEnabled + value.getName() + "§7, \tверсия: §9" + value.getPluginMeta().getVersion());
                            });
                        }
                    }
                    break;

                    case "pl":
                    case "plugins": {
                        var plugins = Bukkit.getPluginManager().getPlugins();
                        sender.sendMessage("§f[§aИнформация§f] §7Список загруженных плагинов сервера:");
                        for (Plugin plugin : plugins) {
                            String isEnabled = plugin.isEnabled() ? "§a" : "§c";
                            sender.sendMessage("§f[§aИнформация§f] §7Плагин: " + isEnabled + plugin.getName() + "§7, \tверсия: §9" + plugin.getPluginMeta().getVersion());
                        }
                    }
                    break;

                    case "dc":
                    case "deletecommand": {
                        var knownCommands = Bukkit.getServer().getCommandMap().getKnownCommands();
                        var removedCommand = knownCommands.get(arguments[1]);
                        if (knownCommands.remove(arguments[1]) != null) {

                            var commands = MlcApi.getApi().getPlugin().getConfig().getStringList("deleted-commands");
                            commands.add(arguments[1]);

                            MlcApi.getApi().getPlugin().getConfig().set("deleted-commands", commands);
                            MlcApi.getApi().getPlugin().saveConfig();

                            MlcApi.getApi().getDeletedCommands().put(arguments[1], removedCommand);
                            sender.sendMessage("§f[§aИнформация§f] §7Команда §9" + arguments[1] + "§7, успешно удалена");
                        } else {
                            sender.sendMessage("§f[§cОшибка§f] §7Команды §9" + arguments[1] + "§7, не существует");
                        }
                    }
                    break;

                    case "rc":
                    case "restorecommand": {
                        var restoredCommand = MlcApi.getApi().getDeletedCommands().get(arguments[1]);
                        if (restoredCommand != null) {

                            var commands = MlcApi.getApi().getPlugin().getConfig().getStringList("deleted-commands");
                            commands.remove(arguments[1]);

                            MlcApi.getApi().getPlugin().getConfig().set("deleted-commands", commands);
                            MlcApi.getApi().getPlugin().saveConfig();

                            sender.sendMessage("§f[§aИнформация§f] §7Команда §9" + arguments[1] + "§7, успешно восстановлена перезапустите сервер");
                        } else {
                            sender.sendMessage("§f[§cОшибка§f] §7Команды §9" + arguments[1] + "§7, не существует");
                        }
                    }
                    break;

                    default:
                        sender.sendMessage("§f[§aИнформация§f] §7Список команд MineLabCorePlugins");
                        sender.sendMessage("§f[§aИнформация§f] §7- /mlcp load               <PluginName>    // Загружает плагин");
                        sender.sendMessage("§f[§aИнформация§f] §7- /mlcp unload             <PluginName>    // Выгружает плагин");
                        sender.sendMessage("§f[§aИнформация§f] §7- /mlcp reload             <PluginName>    // Перезагружает плагин");
                        sender.sendMessage("§f[§aИнформация§f] §7- /mlcp enable             <PluginName>    // Включает плагин");
                        sender.sendMessage("§f[§aИнформация§f] §7- /mlcp disable            <PluginName>    // Выключает плагин");
                        sender.sendMessage("§f[§aИнформация§f] §7- /mlcp list                               // Показывает список загруженных плагинов зависящих от MineLabCore");
                        sender.sendMessage("§f[§aИнформация§f] §7- /mlcp plugins                            // Показывает список загруженных плагинов");
                        sender.sendMessage("§f[§aИнформация§f] §7- /mlcp deletecommand      <CommandName>   // Удаляет указанную команду");
                        sender.sendMessage("§f[§aИнформация§f] §7- /mlcp restorecommand     <CommandName>   // Восстанавливает указанную команду");
                        break;
                }
            } else {
                sender.sendMessage("§f[§aИнформация§f] §fНедостаточно прав.");
            }
        });
        return true;
    }

    @Override
    public List<String> onTabCompleteEvent(TabCompleteArgument tabCompleteArgument) {

        var arguments = tabCompleteArgument.getArguments();
        var alias = tabCompleteArgument.getAlias();
        var sender = tabCompleteArgument.getSender();

        if (sender.isOp() || sender.hasPermission("mlc.core")) {
            if (arguments.length == 1) {
                return List.of("load", "unload", "reload", "enable", "disable", "list", "plugins", "deletecommand", "restorecommand");
            } else if (arguments.length == 2 && (arguments[0].equalsIgnoreCase("unload")
                    || arguments[0].equalsIgnoreCase("reload")
                    || arguments[0].equalsIgnoreCase("enable")
                    || arguments[0].equalsIgnoreCase("disable"))) {

                var plugins = Bukkit.getPluginManager().getPlugins();
                List<String> list = new ArrayList<>();

                for (Plugin plugin : plugins) {
                    list.add(plugin.getName());
                }

                return list;
            } else if (arguments.length == 2 && (arguments[0].equalsIgnoreCase("deletecommand") || arguments[0].equalsIgnoreCase("dc"))) {

                var knownCommands = Bukkit.getServer().getCommandMap().getKnownCommands();
                List<String> list = new ArrayList<>();

                for (Command command : knownCommands.values()) {
                    list.add(command.getName());
                }

                return list;
            } else if (arguments.length == 2 && (arguments[0].equalsIgnoreCase("restorecommand") || arguments[0].equalsIgnoreCase("rc"))) {

                return MlcApi.getApi().getPlugin().getConfig().getStringList("deleted-commands");
            }
        }

        return List.of("У вас недостаточно прав!");
    }

    public CodeExecutor getExecutor() {
        return executor;
    }

    public void setExecutor(CodeExecutor executor) {
        this.executor = executor;
    }

    public Plugin getPlugin(String pluginName, CommandSender sender) {

        Plugin plugin = Bukkit.getPluginManager().getPlugin(pluginName);
        if (plugin == null) {
            sender.sendMessage("§f[§cОшибка§f] §7Плагина " + pluginName + " не существует");
            return null;
        }

        return plugin;
    }
}
