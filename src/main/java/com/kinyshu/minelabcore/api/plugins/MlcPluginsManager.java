package com.kinyshu.minelabcore.api.plugins;

import com.kinyshu.minelabcore.api.core.MlcApi;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.event.Event;
import org.bukkit.plugin.*;
import org.bukkit.plugin.java.PluginClassLoader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

public class MlcPluginsManager {

    public boolean loadPlugin(String fileName) {

        File directory = new File("plugins");
        File pluginFile = new File(directory, fileName);

        if (!directory.exists() || !pluginFile.isFile()) {
            MlcApi.getApi().getLogger().log("§7Неверный файл или директория файла: §9" + pluginFile.getPath());
            return false;
        }

        Plugin plugin = null;

        try {
            plugin = Bukkit.getPluginManager().loadPlugin(pluginFile);
        }
        catch (InvalidDescriptionException | InvalidPluginException exception) {
            exception.printStackTrace();
            return false;
        }

        plugin.onLoad();
        Bukkit.getPluginManager().enablePlugin(plugin);

        return true;
    }

    public boolean unloadPlugin(Plugin plugin) {

        if (plugin == null) {
            return false;
        }

        boolean reloadListeners = true;

        List<Plugin> plugins = new ArrayList<>();
        Map<String, Plugin> names = new HashMap<>();
        Map<String, Command> commands = new HashMap<>();
        Map<Event, SortedSet<RegisteredListener>> listeners = null;

        String pluginName = plugin.getName();
        PluginManager pluginManager = Bukkit.getServer().getPluginManager();
        SimpleCommandMap commandMap;

        try {

            Field pluginsField = pluginManager.getClass().getDeclaredField("plugins");
            pluginsField.setAccessible(true);
            plugins = (List<Plugin>) pluginsField.get(pluginManager);

            Field lookupNamesField = pluginManager.getClass().getDeclaredField("lookupNames");
            lookupNamesField.setAccessible(true);
            names = (Map<String, Plugin>) lookupNamesField.get(pluginManager);

            try {
                Field listenersField = pluginManager.getClass().getDeclaredField("listeners");
                listenersField.setAccessible(true);
                listeners = (Map<Event, SortedSet<RegisteredListener>>) listenersField.get(pluginManager);
            } catch (Exception exception) {
                reloadListeners = false;
                MlcApi.getApi().getLogger().log("Перезагрузка прослушивателей отменена");
            }

            Field commandMapField = pluginManager.getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            commandMap = (SimpleCommandMap) commandMapField.get(pluginManager);

            Field knownCommandsField = SimpleCommandMap.class.getDeclaredField("knownCommands");
            knownCommandsField.setAccessible(true);
            commands = (Map<String, Command>) knownCommandsField.get(commandMap);

        } catch (NoSuchFieldException | IllegalAccessException exception) {
            exception.printStackTrace();
            return false;
        }

        pluginManager.disablePlugin(plugin);
        
        if (plugins.contains(plugin)) {
            plugins.remove(plugin);
            MlcApi.getApi().getLogger().log("Плагин " + pluginName + " удалён из списка плагинов");
        }

        if (names.containsKey(pluginName)){
            MlcApi.getApi().getLogger().log("Плагин " + pluginName + " удалён из списка имён");
        }

        if (reloadListeners) {
            for (SortedSet<RegisteredListener> set : listeners.values()) {
                for (Iterator<RegisteredListener> it = set.iterator(); it.hasNext(); ) {

                    RegisteredListener registeredListener = it.next();
                    if (registeredListener.getPlugin() == plugin) {
                        it.remove();
                    }
                }
            }
        }

        for (Iterator<Map.Entry<String, Command>> it = commands.entrySet().iterator(); it.hasNext(); ) {

            Map.Entry<String, Command> entry = it.next();
            if (entry.getValue() instanceof PluginCommand) {

                PluginCommand pluginCommand = (PluginCommand) entry.getValue();
                if (pluginCommand.getPlugin() == plugin) {
                    pluginCommand.unregister(commandMap);
                    it.remove();

                    MlcApi.getApi().getLogger().log("Команда " + pluginCommand.getName() + " удалена");
                }
            }
        }

        ClassLoader classLoader = plugin.getClass().getClassLoader();

        if (classLoader instanceof PluginClassLoader) {

            try {
                Field pluginField = classLoader.getClass().getDeclaredField("plugin");
                pluginField.setAccessible(true);
                pluginField.set(classLoader, null);

                Field pluginInitField = classLoader.getClass().getDeclaredField("pluginInit");
                pluginInitField.setAccessible(true);
                pluginInitField.set(classLoader, null);

            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException exception) {
                exception.printStackTrace();
            }

            try {
                ((PluginClassLoader) classLoader).close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        System.gc();

        return true;
    }

    public boolean enablePlugin(Plugin plugin) {
        if (plugin.isEnabled()) {
            return false;
        }

        Bukkit.getPluginManager().enablePlugin(plugin);
        return true;
    }

    public boolean disablePlugin(Plugin plugin) {
        if (!plugin.isEnabled()) {
            return true;
        }

        Bukkit.getPluginManager().disablePlugin(plugin);
        return !plugin.isEnabled();
    }
}
