package br.com.strixcloud.lstop.services.hologram.update;

import br.com.strixcloud.lstop.provider.hologram.IHologramProvider;
import br.com.strixcloud.lstop.provider.log.IStrixLogger;
import br.com.strixcloud.lstop.services.hologram.setup.HologramSetupController;
import lombok.AllArgsConstructor;
import lombok.var;

@AllArgsConstructor
public class HologramUpdateService {

    private final IHologramProvider hologramProvider;
    private final IStrixLogger logger;

    public void execute() {
        logger.info("Updating top holograms");
        var setupController = HologramSetupController.getInstance();
        hologramProvider.getHolograms().forEach(setupController::handle);
    }

}
