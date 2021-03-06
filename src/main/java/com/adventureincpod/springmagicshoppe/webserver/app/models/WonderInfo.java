package com.adventureincpod.springmagicshoppe.webserver.app.models;


import com.adventureincpod.springmagicshoppe.webserver.app.models.crud.StoredItems;
import com.adventureincpod.springmagicshoppe.webserver.app.models.crud.Wonder;
import com.adventureincpod.springmagicshoppe.webserver.app.models.enums.Rarity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class WonderInfo extends ItemInfo{
    Wonder wonder;
    Integer gold;
    Integer stones;
    Integer charges;

    public WonderInfo(Wonder wonder, Shop shop) {
        super();
        this.wonder = wonder;
        this.stones = generateStones(Rarity.valueOf(wonder.getRarity().toUpperCase().replace(" ", "")));
        this.charges = generateCharges();
        this.gold = generateGold(shop);
    }

    public WonderInfo(Wonder wonder, StoredItems item) {
        super(item.getOnSale());
        this.wonder = wonder;
        this.stones = item.getStones();
        this.charges = item.getCharges();
        this.gold = item.getGold();
    }

    private Integer generateStones(Rarity rarity) {
        return randomNum(rarity.getStoneMin(), rarity.getStoneMax());
    }

    private Integer generateCharges() {
        return randomNum(1, 5);
    }

    private Integer generateGold(Shop shop) {
        Rarity rarity = Rarity.valueOf(wonder.getRarity().toUpperCase().replace(" ", ""));
        Double price = calculateBasePrice(shop.getBasePrices(), shop.getDiscounts(),
                rarity, wonder.getType());
        price *= (1 + (charges * .2));
        price *= (1 - (
                (Double.valueOf(stones) - (double) rarity.getStoneMin()) /
                        ((double) rarity.getStoneMax() - rarity.getStoneMin()) * .4));
        return (int) Math.round(price) * 10;
    }
}
