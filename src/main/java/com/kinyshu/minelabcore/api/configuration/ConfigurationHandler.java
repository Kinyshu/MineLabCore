package com.kinyshu.minelabcore.api.configuration;

import com.kinyshu.minelabcore.api.configuration.abstracts.AbstractConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Objects;
import java.util.logging.Level;

public class ConfigurationHandler extends AbstractConfiguration {

    public ConfigurationHandler(String filePath, JavaPlugin javaPlugin) {

        this.setJavaPlugin(javaPlugin);

        this.setFileName(filePath);
        this.setFilePath(this.getJavaPlugin().getDataFolder().getPath() + "\\" + this.getFileName());

        this.reload();
    }

    @Override
    public boolean save() {
        try {
            this.getYamlConfiguration().save(this.getFile());
            return true;
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete() {
        return this.getFile().delete();
    }

    @Override
    public boolean reload() {
        try {
            this.setFile(new File(this.getFilePath()));
            if (!this.getFile().exists()) {
                if (!this.findDefaultConfiguration()) {

                    this.getJavaPlugin().getLogger().log(
                            Level.WARNING,
                            String.format("Фатальная ошибка, не могу найти файл стандартной конфигурации %s.",
                                    this.getFileName())
                    );

                    this.getJavaPlugin().getServer().shutdown();
                }

                if (!this.saveDefaultConfiguration()) {

                    this.getJavaPlugin().getLogger().log(
                            Level.WARNING,
                            String.format("Фатальная ошибка, не могу сохранить файл стандартной конфигурации %s.",
                                    this.getFileName())
                    );

                    this.getJavaPlugin().getServer().shutdown();
                }

                this.getJavaPlugin().getLogger().log(
                        Level.INFO,
                        String.format("Файл %s не был найден, сохраняю стандартный файл конфигурации.",
                                this.getFileName())
                );
            }

            if (this.getFile().exists()) {

                this.getJavaPlugin().getLogger().log(
                        Level.INFO,
                        String.format("Загружаю файл конфигурации %s.",
                                this.getFileName())
                );

                this.setYamlConfiguration(YamlConfiguration.loadConfiguration(this.getFile()));
                return true;
            }

        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean findDefaultConfiguration() {
        return !Objects.equals(this.getJavaPlugin().getResource(this.getFileName()), InputStream.nullInputStream());
    }

    @Override
    public boolean saveDefaultConfiguration() {

        File fileDirectory = new File(this.getFile().getParent());
        if (!fileDirectory.exists() && !fileDirectory.mkdirs()) {
            this.getJavaPlugin().getServer().shutdown();
            return false;
        }

        try {
            Files.copy(Objects.requireNonNull(this.getJavaPlugin().getResource(this.getFileName())), this.getFile().toPath());
            return true;
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }

        return false;
    }
}
