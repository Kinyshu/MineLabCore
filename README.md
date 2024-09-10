# Плагин MineLabCore

MineLabCore — это мощное ядро для сервера Minecraft, предоставляющее надежную платформу для упрощения регистрации многих базовых функций. С помощью этого плагина вы получите все инструменты, необходимые для создания и настройки вашего плагина.

## Реализованно на данный момент
- **Регистратор команд:**  

  Легко добавляйте и управляйте командами, которые могут взаимодействовать с вашими пользователями. 

  

- **Система обработки событий:**  

  Настройте обработку событий, чтобы реагировать на различные действия в вашем плагине. 


- **Управление конфигурациями:**  

  Удобный интерфейс для настройки всех необходимых параметров вашего проекта, обеспечивая гибкость и простоту изменения настроек.

- **Асинхронный вызов функций:**  

  Ядро реализует вызов кода асинхронно используя виртуальные потоки.

- **Совместимость:**  

  MineLabCore легко интегрируется с любыми другими серверными ядрами, позволяя вам создавать мощные и масштабируемые решения.

  
## gradle

> [!NOTE]
> Прежде чем добавить зависимость соберите проект, и переместите jar файл в папку libraries вашего проекта.

```
dependencies {
    implementation files('libraries/MineLabCore-release.jar')
}
```

## plugin.yml

```
name: TestPlugin
version: '1.00'
main: com.kinyshu.testplugin.main
api-version: '1.21'
depend: [MineLabCore]
```

## Регистрация плагина и команды

> [!NOTE]
> Регистрация плагина не обязательна

```
@Override
public void onEnable() {

    var api = MlcApi.getApi();
    api.registerPlugin(this);

    var commandRegistrar = new CommandRegistrar(this);
    commandRegistrar.registerCommand(new TestCommand());
}
```

## TestCommand

```
public class TestCommand extends AbstractCommand {

    public TestCommand() {

        this.setName("test");
        this.setDescription("Команда плагина TestPlugin");
        this.setUsage("/test");
        this.setAliases(List.of("t", "tc"));

        this.setExecutor(new TestCommandExecutor(this));
    }
}
```

## TestCommandExecutor

```
public class TestCommandExecutor extends AbstractCommandExecutor {
    
    public TestCommandExecutor(TestCommand testCommand) {
        super(testCommand);
    }

    @Override
    public boolean onCommandExecuted(ExecuteArgument executeArgument) {

        if (executeArgument.getSender() instanceof Player player) {
            player.sendMessage("§f[§aИнформация§f] §7Это тестовая команда плагина TestPlugin ^_^");
            player.playSound(player.getLocation(), Sound.ENTITY_CAT_PURREOW, 1.f, 1.f);
        }

        return true;
    }

    @Override
    public List<String> onTabCompleteEvent(TabCompleteArgument tabCompleteArgument) {
        return List.of("meow");
    }
}
```
