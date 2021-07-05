package com.adventureincpod.springmagicshoppe.webserver.app.models;

import com.adventureincpod.springmagicshoppe.webserver.app.models.crud.Scroll;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ScrollInfo extends ItemInfo{
    Scroll scroll;
    Integer gold;
    Integer spellLevel;
    Rarity leveledRarity;

    public ScrollInfo(Scroll scroll, Shop shop) {
        this.scroll = scroll;
        String level = scroll.getLevel();
        this.spellLevel = level.charAt(0) == 'C' ? 0 : Integer.parseInt(level.substring(0,1));
        this.leveledRarity = getRarity();
        this.gold = generateGold(shop);
    }

    public Integer generateGold(Shop shop) {
        return (int) Math.round(calculateBasePrice(shop.getBasePrices(), shop.getDiscounts(), leveledRarity, scroll.getType()));
    }

    private Rarity getRarity() {
        Rarity rarity = Rarity.COMMON;
        for(Rarity rare: Rarity.values()) {
            if (spellLevel >= rare.getSpellLevelMin() && spellLevel <= rare.getSpellLevelMax()) {
                rarity = rare;
            }
        }
        return rarity;
    }
}
