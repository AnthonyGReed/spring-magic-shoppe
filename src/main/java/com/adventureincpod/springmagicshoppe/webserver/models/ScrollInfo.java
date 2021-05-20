package com.adventureincpod.springmagicshoppe.webserver.models;

import com.adventureincpod.springmagicshoppe.webserver.models.crud.Scroll;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ScrollInfo extends ItemInfo{
    Scroll scroll;
    Integer gold;
    Integer spellLevel;

    public ScrollInfo(Scroll scroll, Shop shop) {
        this.scroll = scroll;
        String level = scroll.getLevel();
        this.spellLevel = level.charAt(0) == 'C' ? 0 : Integer.parseInt(level.substring(0,1));
        this.gold = generateGold(shop);
    }

    public Integer generateGold(Shop shop) {
        Rarity rarity = Rarity.COMMON;
        for(Rarity rare: Rarity.values()) {
            if (spellLevel >= rare.getSpellLevelMin() && spellLevel <= rare.getSpellLevelMax()) {
                rarity = rare;
            }
        }
        return (int) Math.round(calculateBasePrice(shop.getBasePrices(), shop.getDiscounts(), rarity, scroll.getType()));
    }
}
