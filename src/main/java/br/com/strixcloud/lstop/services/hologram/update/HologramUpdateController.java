package br.com.strixcloud.lstop.services.hologram.update;

import br.com.strixcloud.lstop.StrixLSTop;
import lombok.Getter;
import lombok.var;

public class HologramUpdateController {

    @Getter
    private static final HologramUpdateController instance = new HologramUpdateController();

    private final HologramUpdateService service;

    public HologramUpdateController() {
        var hologramProvider = StrixLSTop.getInstance().getHologramProvider();
        var logger = StrixLSTop.getInstance().getSLogger();
        this.service = new HologramUpdateService(hologramProvider, logger);
    }

    public void handle() {
        service.execute();
    }
    
}
