package br.com.strixcloud.lstop.entities.util;

import lombok.Getter;
import lombok.var;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ConfigFile {

    @Getter private final JavaPlugin plugin;
    @Getter private final File file;
    private final String name;
    private YamlConfiguration config;

    public ConfigFile(JavaPlugin plugin, String name) {
        this.plugin = plugin;
        this.name = name;
        file = new File(plugin.getDataFolder(),name);
        loadConfig();
    }

    public YamlConfiguration getConfig() {
        return config;
    }

    public void saveConfig() {
        try {
            getConfig().save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveDefaultConfig() {
        plugin.saveResource(name, false);
    }

    public void loadConfig(){
        if(!file.exists()) saveDefaultConfig();
        reloadConfig();
    }

    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(file);
    }

    public String getMessage(String path){
        return Objects.requireNonNull(config.getString(path)).replace("&","§");
    }

    public List<String> getListMessage(String path){
        var replace = getConfig().getStringList(path);
        for(String s : getConfig().getStringList(path)) {
            replace.remove(s);
            s = s.replace("&", "§");
            replace.add(s);
        }
        return replace;
    }

}
