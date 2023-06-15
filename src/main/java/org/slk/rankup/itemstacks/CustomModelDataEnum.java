package org.slk.rankup.itemstacks;

public enum CustomModelDataEnum {
    FIREWORK_BOX(6000),
    ENCHATING_BOOK(6001),
    TICKET(6002);

    private final int value;

    CustomModelDataEnum(int value) {
        this.value = value;
    }

    public int get() { return value; }
}