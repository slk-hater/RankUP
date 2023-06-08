package org.slk.rankup.placeables;

public abstract class IPlaceableComponents {
    protected boolean canBeDestroyed = true;
    protected boolean dropOnDestroy = true;
    protected int autoDestroyDelay = -1;
}