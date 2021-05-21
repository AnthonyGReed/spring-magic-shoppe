package com.adventureincpod.springmagicshoppe.webserver.models;

import com.adventureincpod.springmagicshoppe.webserver.models.crud.Potion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PotionInfo extends ItemInfo {
    Potion potion;
    Integer gold;
    Integer doses;

    public PotionInfo(Potion potion, Shop shop) {
        super();
        this.potion = potion;
        this.doses = generateDoses();
        this.gold = generateGold(shop);
    }

    private Integer generateDoses() {
        return randomNum(1, 5);
    }

    private Integer generateGold(Shop shop) {
        Rarity rarity = Rarity.valueOf(potion.getRarity().toUpperCase().replace(" ", ""));
        Double price = calculateBasePrice(shop.getBasePrices(), shop.getDiscounts(), rarity, potion.getType());
        price *= (1 + (doses * .2));
        return (int) Math.round(price) * 10;
    }
}