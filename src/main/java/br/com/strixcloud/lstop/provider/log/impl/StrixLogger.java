package br.com.strixcloud.lstop.provider.log.impl;

import br.com.strixcloud.lstop.entities.util.ConfigFile;
import br.com.strixcloud.lstop.provider.log.IStrixLogger;
import br.com.strixcloud.lstop.provider.log.LogType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.CompletableFuture;

public class StrixLogger implements IStrixLogger {

    private final ConsoleLogger consoleLogger;
    private final FileLogger fileLogger;

    public StrixLogger(JavaPlugin plugin, boolean console, boolean file) {
        consoleLogger = console ? new ConsoleLogger(plugin) : null;
        fileLogger = file ? new FileLogger(plugin) : null;
    }

    public StrixLogger(JavaPlugin plugin, ConfigFile config) {
        boolean console = config.getConfig().getBoolean("Debug.console-log");
        boolean file = config.getConfig().getBoolean("Debug.file-log");
        consoleLogger = console ? new ConsoleLogger(plugin) : null;
        fileLogger = file ? new FileLogger(plugin) : null;
    }

    public void info(String message) {
        log(LogType.INFO, message);
    }

    public void warn(String message) {
        log(LogType.WARN, message);
    }

    public void error(String message) {
        log(LogType.ERROR, message);
    }

    private void log(LogType type, String msg) {
        if(consoleLogger != null) {
            consoleLogger.log(type, msg);
        }
        if(fileLogger != null) {
            CompletableFuture.runAsync(() -> {
                fileLogger.log(type, msg);
            });
        }
    }

}
