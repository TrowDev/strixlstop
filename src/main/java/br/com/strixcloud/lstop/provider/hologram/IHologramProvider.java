package br.com.strixcloud.lstop.provider.hologram;

import br.com.strixcloud.lstop.entities.data.IPlacedHologram;
import org.bukkit.Location;

import java.util.List;

public interface IHologramProvider {

    IPlacedHologram create(Location location);

    void delete(IPlacedHologram hologram);

    List<IPlacedHologram> getHolograms();

}
