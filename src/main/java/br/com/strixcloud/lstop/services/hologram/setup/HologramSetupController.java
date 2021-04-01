package br.com.strixcloud.lstop.services.hologram.setup;

import br.com.strixcloud.lstop.StrixLSTop;
import br.com.strixcloud.lstop.entities.data.IPlacedHologram;
import lombok.Getter;
import lombok.var;

public class HologramSetupController {

    @Getter
    private static final HologramSetupController instance = new HologramSetupController();

    private final HologramSetupService service;

    public HologramSetupController() {
        var displayProvider = StrixLSTop.getInstance().getDisplayProvider();
        this.service = new HologramSetupService(displayProvider);
    }

    public void handle(IPlacedHologram hologram) {
        service.execute(hologram);
    }
    
}
