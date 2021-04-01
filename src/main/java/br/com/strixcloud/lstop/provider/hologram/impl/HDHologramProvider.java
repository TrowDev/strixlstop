package br.com.strixcloud.lstop.provider.hologram.impl;

import br.com.strixcloud.lstop.StrixLSTop;
import br.com.strixcloud.lstop.entities.data.IPlacedHologram;
import br.com.strixcloud.lstop.entities.data.impl.HDPlacedHologram;
import br.com.strixcloud.lstop.provider.hologram.IHologramProvider;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import lombok.var;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class HDHologramProvider implements IHologramProvider {
    @Override
    public IPlacedHologram create(Location location) {
        return new HDPlacedHologram(location);
    }

    @Override
    public void delete(IPlacedHologram hologram) {
        hologram.delete();
    }

    @Override
    public List<IPlacedHologram> getHolograms() {
        List<IPlacedHologram> holos = new ArrayList<>();
        for (var holo : HologramsAPI.getHolograms(StrixLSTop.getInstance())) {
            holos.add(new HDPlacedHologram(holo));
        }
        return holos;
    }
}
