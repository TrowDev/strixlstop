package br.com.strixcloud.lstop.provider.log.impl;

import br.com.strixcloud.lstop.provider.log.ILogger;
import br.com.strixcloud.lstop.provider.log.LogType;
import lombok.Data;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class FileLogger implements ILogger {

    private final JavaPlugin plugin;
    private ConsoleLogger logger;
    private File logsFile;

    public FileLogger(JavaPlugin plugin) {
        this.plugin = plugin;
        logger = new ConsoleLogger(plugin);
        createFile();
    }

    public void log(LogType type, String message) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm");
        writeMessage("[" + formatter.format(new Date()) + " " + type.getFile()+ "]" + " " + message);
    }

    /*
        Methods
     */

    private void writeMessage(String message) {
        try {
            FileWriter writer = new FileWriter(logsFile, true);
            writer.write("\n"+message);
            writer.flush();
        } catch (IOException e) {
            logger.log(LogType.ERROR, "An error occurred when trying to write in the logs file");
        }
    }

    private void createFile() {
        try {
            if(!plugin.getDataFolder().exists()){
                plugin.getDataFolder().mkdir();
            }
            File file = new File(plugin.getDataFolder(), "logs.txt");
            if(!file.exists()) {
                if(file.createNewFile()) {
                    logger.log(LogType.INFO, "The logs file has been created in the plugin folder");
                }

            }
            logsFile = file;
        }catch (IOException e) {
            logger.log(LogType.ERROR, "An error occurred when trying to create the logs file");
            e.printStackTrace();
        }
    }

}
