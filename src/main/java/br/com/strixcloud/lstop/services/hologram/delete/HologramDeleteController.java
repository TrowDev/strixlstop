package br.com.strixcloud.lstop.services.hologram.delete;

import br.com.strixcloud.lstop.StrixLSTop;
import lombok.Getter;
import lombok.var;

public class HologramDeleteController {

    @Getter
    private static final HologramDeleteController instance = new HologramDeleteController();

    private final HologramDeleteService service;

    public HologramDeleteController() {
        var hologramProvider = StrixLSTop.getInstance().getHologramProvider();
        var storageProvider = StrixLSTop.getInstance().getStorageProvider();
        this.service = new HologramDeleteService(hologramProvider, storageProvider);
    }

    public void handle() {
        service.execute();
    }
    
}
