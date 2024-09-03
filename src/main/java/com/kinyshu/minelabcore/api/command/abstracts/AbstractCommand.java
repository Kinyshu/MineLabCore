package com.kinyshu.minelabcore.api.command.abstracts;

import org.bukkit.plugin.java.JavaPlugin;
import java.util.List;

/**
 * Класс AbstractCommand
 * ~
 * Класс Основной конструкции команды
 * в нём указывается конфигурация
 * команды, параметры и исполнитель
 * ~
 * Класс может принимать в конструктор
 * объект JavaPlugin, и может быть пустым
 * если конструктор пуст JavaPlugin
 * инизиализирует класс CommandRegistrar
 */
public abstract class AbstractCommand {

    // Ссылка на объект исполнителя команды
    private AbstractCommandExecutor executor;

    // Название команды, а также её первоначальная форма выполнения (/commandname)
    private String name;

    // Описание команды
    private String description;

    // Использование команды
    // Например: /commandname <Player>
    private String usage;

    // Список псевдонимов команды
    // Например: /commandname, /cn, /commandn, /cname
    private List<String> aliases;

    // Ссылка на объект плагина в котором регистрируется команда
    private JavaPlugin javaPlugin;

    public AbstractCommand() { }

    public AbstractCommand(JavaPlugin javaPlugin) {
        this.setJavaPlugin(javaPlugin);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsage() {
        return this.usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public List<String> getAliases() {
        return this.aliases;
    }

    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }

    public JavaPlugin getJavaPlugin() {
        return this.javaPlugin;
    }

    public void setJavaPlugin(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;
    }

    public AbstractCommandExecutor getExecutor() {
        return this.executor;
    }

    public void setExecutor(AbstractCommandExecutor executor) {
        this.executor = executor;
    }
}
