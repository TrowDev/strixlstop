package br.com.strixcloud.lstop.entities.util.serializer.impl;

import br.com.strixcloud.lstop.entities.util.serializer.Serializer;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationSerializer implements Serializer<Location, String> {

    @Getter
    private static final LocationSerializer instance = new LocationSerializer();

    @Override
    public String serialize(Location value) {
        return value.getWorld().getName()+";"+value.getX()+";"+value.getY()+";"+value.getZ()+";"+value.getYaw()+";"+value.getPitch();
    }

    @Override
    public Location deserialize(String value) {
        String[] split = value.split(";");
        return new Location(Bukkit.getWorld(split[0]),
                Double.parseDouble(split[1]),
                Double.parseDouble(split[2]),
                Double.parseDouble(split[3]),
                Float.parseFloat(split[4]),
                Float.parseFloat(split[5]));
    }
}
