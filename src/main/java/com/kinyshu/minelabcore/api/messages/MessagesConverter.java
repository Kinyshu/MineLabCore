package com.kinyshu.minelabcore.api.messages;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MessagesConverter {

    public String convertToString(@NotNull List<String> messages) {
        return String.join("", messages);
    }

    public Component convertToComponent(@NotNull String message) {
        return MiniMessage.miniMessage().deserialize(message);
    }

    public Component convertToComponent(@NotNull List<String> messages) {
        return MiniMessage.miniMessage().deserialize(String.join("", messages));
    }
}