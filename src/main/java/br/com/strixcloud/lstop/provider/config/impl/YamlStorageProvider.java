package br.com.strixcloud.lstop.provider.config.impl;

import br.com.strixcloud.lstop.entities.data.IPlacedHologram;
import br.com.strixcloud.lstop.entities.util.ConfigFile;
import br.com.strixcloud.lstop.entities.util.serializer.impl.LocationSerializer;
import br.com.strixcloud.lstop.provider.config.IStorageProvider;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class YamlStorageProvider implements IStorageProvider {
    private final ConfigFile storage;

    @Override
    public void save(IPlacedHologram hologram) {
        var serialized = LocationSerializer.getInstance().serialize(hologram.getLocation());

        List<String> locsList = storage.getConfig().contains("Holograms")
                ? storage.getConfig().getStringList("Holograms") : new ArrayList<>();
        locsList.add(serialized);

        storage.getConfig().set("Holograms", locsList);
        storage.saveConfig();
    }

    @Override
    public List<Location> get() {
        List<String> locsList = storage.getConfig().contains("Holograms")
                ? storage.getConfig().getStringList("Holograms") : new ArrayList<>();
        List<Location> locs = new ArrayList<>();
        for (String s : locsList) {
            locs.add(LocationSerializer.getInstance().deserialize(s));
        }
        return locs;
    }

    @Override
    public void clear() {
        storage.getConfig().set("Holograms", new ArrayList<>());
        storage.saveConfig();
    }
}
