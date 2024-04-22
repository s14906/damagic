package org.bochnacki.damagic.util;

import org.bukkit.Location;

public class LocationChanger {
    public static Location changeLocation(Location location, int x, int y, int z) {
        location.add(x, y, z);
        return location;
    }
}
