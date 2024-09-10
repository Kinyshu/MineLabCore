package com.kinyshu.minelabcore.api.messages;

import com.kinyshu.minelabcore.api.configuration.ConfigurationHandler;
import net.kyori.adventure.text.Component;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MessagesRegistrar {

    private ConfigurationHandler configuration;
    private MessagesConverter converter;

    public MessagesRegistrar(JavaPlugin javaPlugin, String messagesPath) {
        this.setConfiguration(new ConfigurationHandler(messagesPath, javaPlugin));
        this.setConverter(new MessagesConverter());
    }

    public ConfigurationHandler getConfiguration() {
        return this.configuration;
    }

    public void setConfiguration(ConfigurationHandler configuration) {
        this.configuration = configuration;
    }


    public MessagesConverter getConverter() {
        return this.converter;
    }

    public void setConverter(MessagesConverter converter) {
        this.converter = converter;

    }

    public Component getMessage(String key) {
        var yaml = this.getConfiguration().getYamlConfiguration();
        if (yaml.get(key) instanceof String) {
            var message = yaml.getString(key);
            var subStrings = StringUtils.substringsBetween(message, "%", "%");
            if (subStrings != null) {
                for (var argument : subStrings) {
                    message = message.replace("%" + argument + "%", yaml.getString(argument));
                }
            }
            return this.getConverter().convertToComponent(message);
        }
        else if (yaml.get(key) instanceof List<?>) {

            var messages = yaml.getStringList(key);
            var message = this.getConverter().convertToString(messages);

            var subStrings = StringUtils.substringsBetween(message, "%", "%");
            if (subStrings != null) {
                for (var argument : subStrings) {
                    message = message.replace("%" + argument + "%", yaml.getString(argument));
                }
            }

            return this.getConverter().convertToComponent(message);
        }

        return null;
    }

    public Component getMessage(String key, Map<String, String> replaceWith) {
        var yaml = this.getConfiguration().getYamlConfiguration();
        if (yaml.get(key) instanceof String) {

            var message = yaml.getString(key);
            for (var entries : replaceWith.entrySet()) {
                message = message.replace(entries.getKey(), yaml.getString(entries.getValue()));
            }

            return this.getConverter().convertToComponent(message);
        }
        else if (yaml.get(key) instanceof List<?>) {

            var messages = yaml.getStringList(key);
            var message = this.getConverter().convertToString(messages);

            for (var entries : replaceWith.entrySet()) {
                message = message.replace(entries.getKey(), yaml.get(entries.getValue()) == null ? entries.getValue() : yaml.getString(entries.getValue()));
            }

            return this.getConverter().convertToComponent(message);
        }

        return null;
    }
}
