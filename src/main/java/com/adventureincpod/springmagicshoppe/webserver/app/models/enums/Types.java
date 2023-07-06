package com.adventureincpod.springmagicshoppe.webserver.app.models.enums;

/**
 * This is a very simple ENUM of the various types of items possible.
 * @author AGReed
 */
public enum Types {
    SCROLL("Scroll"),
    WEAPON("Weapon"),
    WONDROUSITEM("Wondrous Item"),
    RING("Ring"),
    WAND("Wand"),
    STAFF("Staff"),
    ARMOR("Armor"),
    POTION("Potion"),
    ROD("Rod");

    private final String NAME;

    Types(String name) {
        this.NAME = name;
    }

    public String getName() { return NAME; }
}
