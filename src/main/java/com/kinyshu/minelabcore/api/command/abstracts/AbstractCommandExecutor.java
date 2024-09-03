package com.kinyshu.minelabcore.api.command.abstracts;

import com.kinyshu.minelabcore.api.command.argument.CommandArgument;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

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
        return onCommandExecuted(new CommandArgument(sender, commandLabel, args));
    }

    /**
     * Функция execute
     *
     * @param commandArgument Объект аргументов команды
     *
     * @Override
     */
    public abstract boolean onCommandExecuted(CommandArgument commandArgument);
}
