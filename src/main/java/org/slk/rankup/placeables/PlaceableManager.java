package org.slk.rankup.placeables;

import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;

public class PlaceableManager {
    static Map<IPlaceable, Location> placedMap = new HashMap<>();

    public static Map<IPlaceable, Location> getMap() { return placedMap; }
    public static void addPlaceable(IPlaceable placeable, Location location) { placedMap.put(placeable, location); }
    public static void removePlaceable(IPlaceable placeable) { placedMap.remove(placeable); }
}