package com.adventureincpod.springmagicshoppe.webserver.app.models;

public enum ShopLevel {
    LEVEL1(Rarity.LEGENDARY, Rarity.VERYRARE, Rarity.RARE, Rarity.UNCOMMON, Rarity.COMMON, "Level 1", 1),
    LEVEL2(Rarity.LEGENDARY, Rarity.VERYRARE, Rarity.RARE, Rarity.COMMON, Rarity.UNCOMMON, "Level 2", 2),
    LEVEL3(Rarity.LEGENDARY, Rarity.VERYRARE, Rarity.COMMON, Rarity.RARE, Rarity.UNCOMMON, "Level 3", 3),
    LEVEL4(Rarity.LEGENDARY, Rarity.COMMON, Rarity.VERYRARE, Rarity.UNCOMMON, Rarity.RARE, "Level 4", 4),
    LEVEL5(Rarity.LEGENDARY, Rarity.COMMON, Rarity.UNCOMMON, Rarity.VERYRARE, Rarity.RARE, "Level 5", 5),
    LEVEL6(Rarity.COMMON, Rarity.LEGENDARY, Rarity.UNCOMMON, Rarity.VERYRARE, Rarity.RARE, "Level 6", 6),
    LEVEL7(Rarity.COMMON, Rarity.UNCOMMON, Rarity.LEGENDARY, Rarity.VERYRARE, Rarity.RARE, "Level 7", 7),
    LEVEL8(Rarity.COMMON, Rarity.UNCOMMON, Rarity.LEGENDARY, Rarity.RARE, Rarity.VERYRARE, "Level 8", 8),
    LEVEL9(Rarity.COMMON, Rarity.UNCOMMON, Rarity.RARE, Rarity.LEGENDARY, Rarity.VERYRARE, "Level 9", 9),
    LEVEL10(Rarity.COMMON, Rarity.UNCOMMON, Rarity.RARE, Rarity.VERYRARE, Rarity.LEGENDARY, "Level 10", 10);

    private final Rarity SLOT1;
    private final Rarity SLOT2;
    private final Rarity SLOT3;
    private final Rarity SLOT4;
    private final Rarity SLOT5;
    private final String NAME;
    private final int NUM;

    ShopLevel(Rarity slot1, Rarity slot2, Rarity slot3, Rarity slot4, Rarity slot5, String name, int num) {
        this.SLOT1 = slot1;
        this.SLOT2 = slot2;
        this.SLOT3 = slot3;
        this.SLOT4 = slot4;
        this.SLOT5 = slot5;
        this.NAME = name;
        this.NUM = num;
    }

    public Rarity getSLOT1() {
        return SLOT1;
    }

    public Rarity getSLOT2() {
        return SLOT2;
    }

    public Rarity getSLOT3() {
        return SLOT3;
    }

    public Rarity getSLOT4() {
        return SLOT4;
    }

    public Rarity getSLOT5() {
        return SLOT5;
    }

    public String getNAME() {
        return NAME;
    }

    public int getNUM() {
        return NUM;
    }
}