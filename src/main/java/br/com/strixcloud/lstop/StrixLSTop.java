package br.com.strixcloud.lstop;

import br.com.strixcloud.lstop.bukkit.listener.LegendChatListener;
import br.com.strixcloud.lstop.data.AccountsDAO;
import br.com.strixcloud.lstop.entities.data.TopAccount;
import br.com.strixcloud.lstop.entities.util.ConfigFile;
import br.com.strixcloud.lstop.entities.util.DateDuration;
import br.com.strixcloud.lstop.provider.hologram.IHologramProvider;
import br.com.strixcloud.lstop.provider.hologram.impl.HDHologramProvider;
import br.com.strixcloud.lstop.provider.lojasquare.ILSProvider;
import br.com.strixcloud.lstop.provider.config.IDisplayProvider;
import br.com.strixcloud.lstop.provider.config.impl.YamlDisplayProvider;
import br.com.strixcloud.lstop.provider.lojasquare.impl.HttpLSProvider;
import br.com.strixcloud.lstop.provider.log.IStrixLogger;
import br.com.strixcloud.lstop.provider.log.impl.StrixLogger;
import br.com.strixcloud.lstop.provider.request.IRequestProvider;
import br.com.strixcloud.lstop.provider.request.impl.HttpRequestProvider;
import br.com.strixcloud.lstop.services.hologram.create.HologramCreateController;
import br.com.strixcloud.lstop.services.lojasquare.load.LojasquareLoadController;
import br.com.strixcloud.lstop.task.LojasquareUpdateTask;
import lombok.Getter;
import lombok.var;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Timer;

public class StrixLSTop extends JavaPlugin {

    @Getter private IRequestProvider requestProvider;
    @Getter private ILSProvider lsProvider;
    @Getter private IDisplayProvider displayProvider;
    @Getter private IHologramProvider hologramProvider;

    @Getter private ConfigFile configFile;
    @Getter private IStrixLogger sLogger;

    private Timer timer;

    public void onEnable() {
        var duration = new DateDuration();
        if (!load()) return;

        AccountsDAO.getInstance().add(new TopAccount("SrBugad0", 5));

        long ms = duration.calculate();
        sLogger.info(String.format("§dLoaded plugin in §f%sms", ms));

        var sender = Bukkit.getConsoleSender();
        sender.sendMessage("§d  ___ _       _     _    ___ _____         ");
        sender.sendMessage("§d / __| |_ _ _(_)_ _| |  / __|_   _|__ _ __ ");
        sender.sendMessage("§d \\__ \\  _| '_| \\ \\ / |__\\__ \\ | |/ _ \\ '_ \\ §7§l"+this.getDescription().getVersion());
        sender.sendMessage("§d |___/\\__|_| |_/_\\_\\____|___/ |_|\\___/ .__/");
        sender.sendMessage("§d                                     |_|   ");
        sender.sendMessage("  §7§lCoding your dreams §8| §5§lStrix Cloud");
        sender.sendMessage("§8 ----------------------------------");
    }

    @Override
    public void onDisable() {
        unload();

        var sender = Bukkit.getConsoleSender();
        sender.sendMessage("§c  ___ _       _     _    ___ _____         ");
        sender.sendMessage("§c / __| |_ _ _(_)_ _| |  / __|_   _|__ _ __ ");
        sender.sendMessage("§c \\__ \\  _| '_| \\ \\ / |__\\__ \\ | |/ _ \\ '_ \\ §7§l"+this.getDescription().getVersion());
        sender.sendMessage("§c |___/\\__|_| |_/_\\_\\____|___/ |_|\\___/ .__/");
        sender.sendMessage("§c                                     |_|   ");
        sender.sendMessage("  §7§lBye Bye §8| §5§lStrix Cloud");
        sender.sendMessage("§8 ----------------------------------");
    }

    private boolean load() {
        setupConfiguration();
        setupLS();
        if (!loadData()) return false;
        setupDisplay();
        loadBukkit();
        loadTasks();
        return true;
    }

    private void unload() {
        if (hasPluginLoaded("HolographicDisplays"))
            hologramProvider.getHolograms().forEach(hologramProvider::delete);
    }

    /*
        Setup
     */

    private void setupConfiguration() {
        configFile = new ConfigFile(this, "config.yml");
    }

    private void setupLS() {
        requestProvider = new HttpRequestProvider();
        sLogger = new StrixLogger(this, configFile);
        var authToken = configFile.getConfig().getString("Lojasquare.secret-key");
        lsProvider = new HttpLSProvider(requestProvider, sLogger, authToken);
    }

    private void setupDisplay() {
        displayProvider = new YamlDisplayProvider(configFile);
        displayProvider.load();

        if (hasPluginLoaded("HolographicDisplays")) {
            hologramProvider = new HDHologramProvider();
            sLogger.info("HolographicDisplays hook initialized, displays holograms enabled");
        } else {
            sLogger.warn("LegendChat not loaded, disabling holograms module");
        }
    }

    private void loadBukkit() {
        if (hasPluginLoaded("Legendchat")) {
            Bukkit.getPluginManager().registerEvents(new LegendChatListener(displayProvider), this);
            sLogger.info("LegendChat hook initialized, tag 'top_donator'");
        } else {
            sLogger.warn("LegendChat not loaded, disabling integration");
        }

        getCommand("topdonators").setExecutor(new TopDonatorsCommand());
    }

    private void loadTasks() {
        timer = new Timer();

        var lsTaskDelay = (long) configFile.getConfig().getInt("Tasks.lojasquare-update") * 60 * 1000;
        timer.schedule(new LojasquareUpdateTask(), lsTaskDelay, lsTaskDelay);
    }

    public boolean hasPluginLoaded(String name) {
        var pl = Bukkit.getPluginManager().getPlugin(name);
        return pl != null && pl.isEnabled();
    }

    /*
        Loading
     */

    private boolean loadData() {
        return LojasquareLoadController.getInstance().handle();
    }

    public static StrixLSTop getInstance() {
        return getPlugin(StrixLSTop.class);
    }
}
