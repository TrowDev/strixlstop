package br.com.strixcloud.lstop.task;

import br.com.strixcloud.lstop.services.hologram.update.HologramUpdateController;
import org.bukkit.scheduler.BukkitRunnable;

public class HologramsUpdateTask extends BukkitRunnable {
    @Override
    public void run() {
        HologramUpdateController.getInstance().handle();
    }
}
