package br.com.strixcloud.lstop.services.hologram.create;

import br.com.strixcloud.lstop.provider.config.IStorageProvider;
import br.com.strixcloud.lstop.provider.hologram.IHologramProvider;
import br.com.strixcloud.lstop.services.hologram.setup.HologramSetupController;
import lombok.AllArgsConstructor;
import lombok.var;
import org.bukkit.Location;

@AllArgsConstructor
public class HologramCreateService {

    private final IHologramProvider hologramProvider;
    private final IStorageProvider storageProvider;

    public void execute(Location location) {
        var placedHolo = hologramProvider.create(location);

        storageProvider.save(placedHolo);
        HologramSetupController.getInstance().handle(placedHolo);
    }

}
