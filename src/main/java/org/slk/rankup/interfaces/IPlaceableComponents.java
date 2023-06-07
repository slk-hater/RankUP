package org.slk.rankup.interfaces;

public abstract class IPlaceableComponents {
    protected boolean canBeDestroyed = true;
    protected boolean dropOnDestroy = true;
    protected int autoDestroyDelay = -1;
}