package br.com.strixcloud.lstop.services.hologram.create;

import br.com.strixcloud.lstop.provider.hologram.IHologramProvider;
import br.com.strixcloud.lstop.services.hologram.setup.HologramSetupController;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.var;
import org.bukkit.Location;

@Data @AllArgsConstructor
public class HologramCreateService {

    private final IHologramProvider hologramProvider;

    public void execute(Location location) {
        var placedHolo = hologramProvider.create(location);

        HologramSetupController.getInstance().handle(placedHolo);
    }

}
