package test.kinyshu.command;

import com.kinyshu.minelabcore.api.command.abstracts.AbstractCommandExecutor;
import com.kinyshu.minelabcore.api.command.argument.CommandArgument;
import com.kinyshu.minelabcore.api.executor.CodeExecutor;
import com.kinyshu.minelabcore.api.executor.async.AsyncExecutor;
import com.kinyshu.minelabcore.api.executor.sync.SyncExecutor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TestCommandExecutor extends AbstractCommandExecutor {

    private CodeExecutor codeExecutor;
    private SyncExecutor syncExecutor;
    private AsyncExecutor asyncExecutor;

    public TestCommandExecutor(TestCommand testCommand) {
        super(testCommand);
        this.setCodeExecutor(new CodeExecutor(this.getCommand().getJavaPlugin()));
        this.setSyncExecutor(this.getCodeExecutor().getSyncExecutor());
        this.setAsyncExecutor(this.getCodeExecutor().getAsyncExecutor());
    }

    @Override
    public boolean onCommandExecuted(CommandArgument commandArgument) {

        this.getAsyncExecutor().execute(() -> {
            if (commandArgument.getSender() instanceof Player player) {
                Bukkit.getLogger().info("UUID: " + player.getUniqueId().toString());
            }
        });

        return true;
    }

    public CodeExecutor getCodeExecutor() {
        return this.codeExecutor;
    }

    public void setCodeExecutor(CodeExecutor codeExecutor) {
        this.codeExecutor = codeExecutor;
    }

    public SyncExecutor getSyncExecutor() {
        return syncExecutor;
    }

    public void setSyncExecutor(SyncExecutor syncExecutor) {
        this.syncExecutor = syncExecutor;
    }

    public AsyncExecutor getAsyncExecutor() {
        return asyncExecutor;
    }

    public void setAsyncExecutor(AsyncExecutor asyncExecutor) {
        this.asyncExecutor = asyncExecutor;
    }
}
