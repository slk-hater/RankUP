package org.slk.rankup.managers;

import org.slk.rankup.interfaces.IPlaceable;

import javax.xml.stream.Location;
import java.util.HashMap;
import java.util.Map;

public class PlaceableManager {
    public static Map<IPlaceable, Location> placedMap = new HashMap<>();

    public static void addPlaceable(IPlaceable placeable, Location location){ placedMap.put(placeable, location); }
    public static void removePlaceable(IPlaceable placeable) { placedMap.remove(placeable); }
}