package br.com.strixcloud.lstop.entities.data;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.Location;

@Data @AllArgsConstructor
public class PlacedHologram {

    private Hologram hologram;
    private Location loc;

}
