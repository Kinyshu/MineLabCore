package com.kinyshu.minelabcore.api.configuration.abstracts;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * Класс реализующий абстрактную модель YAML конфигурации
 */
public abstract class AbstractConfiguration {

    // Указатель на объект JavaPlugin
    private JavaPlugin javaPlugin;
    // Относительное название файла
    private String fileName;
    // Относительный путь к файлу
    private String filePath;
    // Объект файла
    private File file;
    // Объект Yaml конфигурации
    private YamlConfiguration yamlConfiguration;

    public JavaPlugin getJavaPlugin() {
        return this.javaPlugin;
    }

    public void setJavaPlugin(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public File getFile() {
        return this.file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public YamlConfiguration getYamlConfiguration() {
        return this.yamlConfiguration;
    }

    public void setYamlConfiguration(YamlConfiguration yamlConfiguration) {
        this.yamlConfiguration = yamlConfiguration;
    }

    // Сохраняет текущие настройки конфигурации в файл
    public abstract boolean save();
    // Удаляет текущий файл конфигурации
    public abstract boolean delete();
    // Перезагружает файл конфигурации
    public abstract boolean reload();
    // Ищет стандартный файл конфигурации
    public abstract boolean findDefaultConfiguration();
    // Сохраняет стандартный файл конфигурации
    public abstract boolean saveDefaultConfiguration();
}
