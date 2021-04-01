package br.com.strixcloud.lstop.services.hologram.create;

import br.com.strixcloud.lstop.StrixLSTop;
import br.com.strixcloud.lstop.entities.data.IPlacedHologram;
import lombok.Getter;
import lombok.var;
import org.bukkit.Location;

public class HologramCreateController {

    @Getter
    private static final HologramCreateController instance = new HologramCreateController();

    private final HologramCreateService service;

    public HologramCreateController() {
        var hologramProvider = StrixLSTop.getInstance().getHologramProvider();
        this.service = new HologramCreateService(hologramProvider);
    }

    public void handle(Location loc) {
        service.execute(loc);
    }
    
}
