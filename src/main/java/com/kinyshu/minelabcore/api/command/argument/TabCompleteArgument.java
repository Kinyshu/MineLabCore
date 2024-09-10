package com.kinyshu.minelabcore.api.command.argument;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class TabCompleteArgument {
    private CommandSender sender;
    private String alias;
    private String[] arguments;

    public TabCompleteArgument(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        this.setSender(sender);
        this.setAlias(commandLabel);
        this.setArguments(args);
    }

    public CommandSender getSender() {
        return this.sender;
    }

    public void setSender(CommandSender sender) {
        this.sender = sender;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String[] getArguments() {
        return this.arguments;
    }

    public void setArguments(String[] arguments) {
        this.arguments = arguments;
    }
}
