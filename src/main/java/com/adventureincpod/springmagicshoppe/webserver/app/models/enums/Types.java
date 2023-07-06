package com.adventureincpod.springmagicshoppe.webserver.app.models.enums;

/**
 * This is a very simple ENUM of the various types of items possible.
 * @author AGReed
 */
public enum Types {
    SCROLL(name:"Scroll"),
    WEAPON(name:"Weapon"),
    WONDROUSITEM(name:"Wondrous Item"),
    RING(name:"Ring"),
    WAND(name:"Wand"),
    STAFF(name:"Staff"),
    ARMOR(name:"Armor"),
    POTION(name:"Potion"),
    ROD(name:"Rod")

    private final String NAME;

    Types(String name) {
        this.NAME = name;
    }

    public String getName() { return NAME; }
}
