package com.adventureincpod.springmagicshoppe.webserver.models;

import com.adventureincpod.springmagicshoppe.webserver.DAOs.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Setter @Getter
public class Shop {
    private String id;
    private ArrayList<Item> items;
    private ArrayList<Wonder> wonders;
    private ArrayList<Potion> potions;
    private ArrayList<Scroll> scrolls;
    private HashMap<Rarity, Integer> basePrices;
    private HashMap<Types, Integer> discounts;
    private Random random;
    private ShopDAO shopDAO;

    public Shop() {
        this.basePrices = new HashMap<>();
        this.discounts = new HashMap<>();
        this.items = new ArrayList<>();
        this.scrolls = new ArrayList<>();
        this.random = new Random();
        this.shopDAO = new ShopDAO();
        generateBasePrices();
        generateDiscounts();
        this.id = generateShopId();
        generateWonders();
        generatePotions();
        generateScrolls();
    }

    public Shop(String id) {

    }

    private void generateBasePrices() {
        for(Rarity rarity: Rarity.values()) {
            basePrices.put(rarity, randomNum(rarity.getGoldMin(), rarity.getGoldMax()));
        }
    }

    private void generateDiscounts() {
        for(Types type: Types.values()) {
            discounts.put(type, random.nextInt(3));
        }
    }

    private String generateShopId() {
        String checkString = RandomStringUtils.randomAlphanumeric(6).toUpperCase();
        if(!shopDAO.checkID(checkString)) {
            checkString = generateShopId();
        }
        return checkString;
    }

    private void generateWonders() {
        WonderDAO wonderDAO = new WonderDAO();
        List<Wonder> list = wonderDAO.getAll();
        for(int i = 0; i <  15; i++) {
            Wonder wonder = list.get(random.nextInt(list.size()));
            this.items.add(wonder);
            this.wonders.add(wonder);
        }
    }

    private void generatePotions() {
        PotionDAO potionDAO = new PotionDAO();
        List<Potion> list = potionDAO.getAll();
        for(int i = 0; i < 3; i++) {
            Potion potion = list.get(random.nextInt(list.size()));
            this.items.add(potion);
            this.potions.add(potion);
        }
    }

    private void generateScrolls() {
        ScrollDAO scrollDAO = new ScrollDAO();
        List<Scroll> list = scrollDAO.getAll();
        int numberOfScrolls = randomNum(3, 5);
        for(int i = 0; i < numberOfScrolls; i++) {
            Scroll scroll = list.get(random.nextInt(list.size()));
            this.items.add(scroll);
            this.scrolls.add(scroll);
        }
    }

    private Integer randomNum(Integer min, Integer max) {
        return random.nextInt(max - min + 1) + min;
    }
}
