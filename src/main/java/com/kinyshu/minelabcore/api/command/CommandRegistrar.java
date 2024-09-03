package com.kinyshu.minelabcore.api.command;

import com.kinyshu.minelabcore.api.command.abstracts.AbstractCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

/**
 * CommandRegistrar
 * ~
 * Класс регистратора команд, используется
 * для регистрации команд в заданном плагине
 * ~
 */
public class CommandRegistrar {

    // Ссылка на объект плагина в котором регистрируется команда
    private JavaPlugin javaPlugin;

    // Список зарегистрированных команд в текущем объекте CommandRegistrar
    private Map<String, AbstractCommand> registeredCommands;

    public CommandRegistrar(JavaPlugin javaPlugin) {
        this.setJavaPlugin(javaPlugin);
        this.setRegisteredCommands(new HashMap<>());
    }

    private JavaPlugin getJavaPlugin() {
        return javaPlugin;
    }

    private void setJavaPlugin(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;
    }

    public Map<String, AbstractCommand> getRegisteredCommands() {
        return this.registeredCommands;
    }

    private void setRegisteredCommands(Map<String, AbstractCommand> registeredCommands) {
        this.registeredCommands = registeredCommands;
    }

    /**
     * Функция registerCommand
     *
     * @param abstractCommand Ссылка на объект команды
     *
     * @return boolean
     */
    public boolean registerCommand(AbstractCommand abstractCommand) {

        // Если ссылка на объект JavaPlugin в объекте
        // AbstractCommand равна NULL, то мы указываем
        // что она равняется текущему JavaPlugin
        if (abstractCommand.getJavaPlugin() == null) {
            abstractCommand.setJavaPlugin(this.getJavaPlugin());
        }

        // Регистрируем команду
        boolean status = this.getJavaPlugin().getServer().getCommandMap().register(abstractCommand.getName(), abstractCommand.getExecutor());
        if (status) {
            // Если команда зарегистрирована, то добавляем
            // в список команд нашу регистрируемую команду
            this.getRegisteredCommands().put(abstractCommand.getName(), abstractCommand);
        }

        return status;
    }
}
