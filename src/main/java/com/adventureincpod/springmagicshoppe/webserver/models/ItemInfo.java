package com.adventureincpod.springmagicshoppe.webserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Random;

@Getter @Setter @AllArgsConstructor
public class ItemInfo {
    Boolean onSale;
    @JsonIgnore
    transient Random random;

    public ItemInfo() {
        this.random = new Random();
        this.onSale = checkOnSale();
    }

    public ItemInfo(Boolean onSale) {
        this.random = new Random();
        this.onSale = onSale;
    }

    Boolean checkOnSale() {
        return randomNum(0, 15) == 15;
    }

    Integer randomNum(Integer min, Integer max) {
        return random.nextInt(max - min + 1) + min;
    }

    Double calculateBasePrice(HashMap<Rarity, Integer> basePrices, HashMap<Types, Integer> discounts,
                              Rarity rarity, String type) {
        Double price;
        if (!rarity.equals(Rarity.LEGENDARY)) {
            price = Double.valueOf(basePrices.get(rarity));
        } else {
            price = Double.valueOf(randomNum(Rarity.LEGENDARY.getGoldMin(), Rarity.LEGENDARY.getGoldMax()));
        }
        switch (discounts.get(Types.valueOf(type.replace(" ", "").toUpperCase()))) {
            case 0:
                price *= 1.10;
                break;
            case 2:
                price *= .90;
                break;
            default:
                break;
        }
        if (onSale) {
            price *= 0.70;
        }
        return price;
    }
}
