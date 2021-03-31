package br.com.strixcloud.lstop.data;

import br.com.strixcloud.lstop.entities.data.PlacedHologram;
import br.com.strixcloud.lstop.entities.util.DAO;
import org.bukkit.Location;

public class HologramsDAO extends DAO<Location, PlacedHologram> {
    @Override
    public void add(PlacedHologram value) {
        this.OBJECTS.put(value.getLoc(), value);
    }

    @Override
    public void remove(Location key) {
        this.OBJECTS.remove(key);
    }
}
