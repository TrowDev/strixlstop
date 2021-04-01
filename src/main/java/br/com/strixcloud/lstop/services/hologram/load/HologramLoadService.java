package br.com.strixcloud.lstop.services.hologram.load;

import br.com.strixcloud.lstop.provider.config.IStorageProvider;
import br.com.strixcloud.lstop.provider.hologram.IHologramProvider;
import br.com.strixcloud.lstop.provider.log.IStrixLogger;
import br.com.strixcloud.lstop.services.hologram.setup.HologramSetupController;
import lombok.AllArgsConstructor;
import lombok.var;

@AllArgsConstructor
public class HologramLoadService {

    private final IHologramProvider hologramProvider;
    private final IStorageProvider storageProvider;
    private final IStrixLogger logger;

    public void execute() {
        var list = storageProvider.get();
        for (var loc : list) {
            var placedHolo = hologramProvider.create(loc);
            HologramSetupController.getInstance().handle(placedHolo);
        }

        logger.info(String.format("Successfully loaded %s holograms", list.size()));
    }

}
