package br.com.strixcloud.lstop.services.hologram.load;

import br.com.strixcloud.lstop.StrixLSTop;
import lombok.Getter;
import lombok.var;

public class HologramLoadController {

    @Getter
    private static final HologramLoadController instance = new HologramLoadController();

    private final HologramLoadService service;

    public HologramLoadController() {
        var hologramProvider = StrixLSTop.getInstance().getHologramProvider();
        var storageProvider = StrixLSTop.getInstance().getStorageProvider();
        var logger = StrixLSTop.getInstance().getSLogger();
        this.service = new HologramLoadService(hologramProvider, storageProvider, logger);
    }

    public void handle() {
        service.execute();
    }
    
}
