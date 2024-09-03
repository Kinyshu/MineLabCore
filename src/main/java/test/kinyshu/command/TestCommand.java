package test.kinyshu.command;

import com.kinyshu.minelabcore.api.command.abstracts.AbstractCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class TestCommand extends AbstractCommand {

    public TestCommand(JavaPlugin javaPlugin) {
        this.setJavaPlugin(javaPlugin);
        this.setName("test");
        this.setDescription("Команда плагина TestPlugin");
        this.setUsage("/test");
        this.setAliases(List.of("t"));

        this.setExecutor(new TestCommandExecutor(this));
    }
}
