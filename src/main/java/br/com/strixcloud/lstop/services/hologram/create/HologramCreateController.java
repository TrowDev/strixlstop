package br.com.strixcloud.lstop.services.hologram.create;

import br.com.strixcloud.lstop.StrixLSTop;
import lombok.Getter;
import lombok.var;
import org.bukkit.Location;

public class HologramCreateController {

    @Getter
    private static final HologramCreateController instance = new HologramCreateController();

    private final HologramCreateService service;

    public HologramCreateController() {
        var hologramProvider = StrixLSTop.getInstance().getHologramProvider();
        var storageProvider = StrixLSTop.getInstance().getStorageProvider();
        this.service = new HologramCreateService(hologramProvider, storageProvider);
    }

    public void handle(Location loc) {
        service.execute(loc);
    }
    
}
