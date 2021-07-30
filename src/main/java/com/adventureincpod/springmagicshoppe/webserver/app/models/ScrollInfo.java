package com.adventureincpod.springmagicshoppe.webserver.app.models;

import com.adventureincpod.springmagicshoppe.webserver.app.models.crud.Scroll;
import com.adventureincpod.springmagicshoppe.webserver.app.models.crud.StoredItems;
import com.adventureincpod.springmagicshoppe.webserver.app.models.enums.Rarity;
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

    public ScrollInfo(Scroll scroll, Shop shop, Integer spellLevel, Rarity rarity) {
        super();
        this.scroll = scroll;
        this.spellLevel = spellLevel;
        this.leveledRarity = rarity;
        this.gold = generateGold(shop);
    }

    public ScrollInfo(Scroll scroll, StoredItems item) {
        super(item.getOnSale());
        this.scroll = scroll;
        this.spellLevel = item.getSpellLevel();
        this.leveledRarity = getRarity();
        this.gold = item.getGold();
    }

    public Integer generateGold(Shop shop) {
        Double price = calculateBasePrice(shop.getBasePrices(), shop.getDiscounts(), leveledRarity, scroll.getType());
        int spellUpcast = spellLevel - Integer.parseInt(scroll.getLevel().substring(0,1));
        for(int i = 0; i < spellUpcast; i++) {
            price *= .9;
        }
        price += randomNum(0,5);
        return (int) Math.round(price) * 10;
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
