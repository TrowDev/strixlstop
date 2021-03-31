package br.com.strixcloud.lstop.provider.log.impl;

import br.com.strixcloud.lstop.provider.log.ILogger;
import br.com.strixcloud.lstop.provider.log.LogType;
import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Data
public class ConsoleLogger implements ILogger {

    private final JavaPlugin plugin;

    public void log(LogType type, String message) {
        sendToConsole("&8[&5&l"+plugin.getName()+"&8] "+type.getConsole() + " " + message);
    }

    private void sendToConsole(String msg) {
        Bukkit.getConsoleSender().sendMessage(msg.replace("&", "§"));
    }

}
