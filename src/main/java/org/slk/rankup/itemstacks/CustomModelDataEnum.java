package org.slk.rankup.itemstacks;

public enum CustomModelDataEnum {
    FIREWORK_BOX(0),
    ENCHATING_BOOK(1);

    private final int value;

    CustomModelDataEnum(int value) {
        this.value = value;
    }

    public int get() { return value; }
}