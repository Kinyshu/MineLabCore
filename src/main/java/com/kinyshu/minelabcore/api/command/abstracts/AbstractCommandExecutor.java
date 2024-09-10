package com.kinyshu.minelabcore.api.command.abstracts;

import com.kinyshu.minelabcore.api.command.argument.ExecuteArgument;
import com.kinyshu.minelabcore.api.command.argument.TabCompleteArgument;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Класс AbstractCommandExecutor
 * ~
 * Класс исполнителя команды - используется для обработки
 * отправленной команды пользователем/сервером
 */
public abstract class AbstractCommandExecutor extends Command {

    // Ссылка на объект команды
    private AbstractCommand command;

    /**
     * Конструктор класса AbstractCommandExecutor
     *
     * @param command Ссылка на объект команды
     */
    public AbstractCommandExecutor(AbstractCommand command) {
        super(command.getName(), command.getDescription(), command.getUsage(), command.getAliases());
        this.setCommand(command);
    }

    public AbstractCommand getCommand() {
        return this.command;
    }

    public void setCommand(AbstractCommand command) {
        this.command = command;
    }

    /**
     * Функция execute
     *
     * @param sender Отправитель команды
     * @param commandLabel Регистрируемый вид команды
     * @param args Аргументы команды
     *
     * @Override
     */
    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, String[] args) {
        return onCommandExecuted(new ExecuteArgument(sender, commandLabel, args));
    }

    @Override
    @NotNull
    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
        return onTabCompleteEvent(new TabCompleteArgument(sender, alias, args));
    }

    /**
     * Функция onCommandExecuted
     *
     * @param commandArgument Объект аргументов команды
     */
    public abstract boolean onCommandExecuted(ExecuteArgument commandArgument);

    /**
     * Функция onTabCompleteEvent
     *
     * @param tabCompleteArgument Объект аргументов tabComplete
     */
    public abstract List<String> onTabCompleteEvent(TabCompleteArgument tabCompleteArgument);
}
