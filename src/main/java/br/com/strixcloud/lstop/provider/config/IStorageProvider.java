package br.com.strixcloud.lstop.provider.config;

import br.com.strixcloud.lstop.entities.data.IPlacedHologram;
import org.bukkit.Location;

import java.util.List;

public interface IStorageProvider {

    void save(IPlacedHologram hologram);

    List<Location> get();

    void clear();

}
