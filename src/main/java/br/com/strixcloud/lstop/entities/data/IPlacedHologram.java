package br.com.strixcloud.lstop.entities.data;

import org.bukkit.Location;

public interface IPlacedHologram {

    Location getLocation();

    void clear();

    void append(String content);

    void add(int line, String content);

    void remove(int line);

    int size();

    void delete();

}
