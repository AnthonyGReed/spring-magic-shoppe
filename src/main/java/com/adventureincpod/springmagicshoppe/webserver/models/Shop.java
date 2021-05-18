package com.adventureincpod.springmagicshoppe.webserver.models;

import com.adventureincpod.springmagicshoppe.webserver.DAOs.ItemDAO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;

@Setter @Getter
public class Shop {
    private String id;
    private ArrayList<Item> items;
    private ArrayList<Wonder> wonders;
    private ArrayList<Potion> potions;
    private ArrayList<Scroll> scrolls;
    private HashMap<Rarity, Integer> basePrices;
    private HashMap<Types, Integer> discounts;

    public Shop() {
        basePrices = new HashMap<>();
        discounts = new HashMap<>();
        items = new ArrayList<>();
        scrolls = new ArrayList<>();
        generateBasePrices();
        generateDiscounts();
        this.id = generateShopId();
        for(int i = 0; i < 15; i++) {
        }
        for(int i = 0; i < 3; i++) {
        }
        for(int i = 0; i < 5; i++) {
        }
    }
}
