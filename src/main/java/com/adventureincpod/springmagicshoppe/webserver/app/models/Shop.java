package com.adventureincpod.springmagicshoppe.webserver.app.models;

import com.adventureincpod.springmagicshoppe.webserver.app.models.crud.*;
import com.adventureincpod.springmagicshoppe.webserver.app.repos.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.*;

@Setter @Getter
public class Shop {
    @JsonIgnore
    private transient WonderRepository wonders;
    @JsonIgnore
    private transient PotionRepository potions;
    @JsonIgnore
    private transient ScrollRepository scrolls;
    @JsonIgnore
    private transient SessionRepository sessionRepository;
    @JsonIgnore
    private transient StoredItemsRepository storedItemsRepository;
    private String id;
    private ArrayList<WonderInfo> selectedWonders;
    private ArrayList<PotionInfo> selectedPotions;
    private ArrayList<ScrollInfo> selectedScrolls;
    private HashMap<Rarity, Integer> basePrices;
    private HashMap<Types, Integer> discounts;
    @JsonIgnore
    private transient Random random;

    public Shop(WonderRepository wonderRepository, PotionRepository potionRepository, ScrollRepository scrollRepository,
                SessionRepository sessionRepository, StoredItemsRepository storedItemsRepository) {
        this.wonders = wonderRepository;
        this.potions = potionRepository;
        this.scrolls = scrollRepository;
        this.sessionRepository = sessionRepository;
        this.storedItemsRepository = storedItemsRepository;
        this.basePrices = new HashMap<>();
        this.discounts = new HashMap<>();
        this.selectedWonders = new ArrayList<>();
        this.selectedPotions = new ArrayList<>();
        this.selectedScrolls = new ArrayList<>();
        this.random = new Random();
        generateBasePrices();
        generateDiscounts();
        this.id = generateShopId();
        generateWonders();
        generatePotions();
        generateScrolls();
        Sessions session = new Sessions(id, discounts, basePrices);
        sessionRepository.save(session);
        for(WonderInfo wonder : selectedWonders) {
            StoredItems stored = new StoredItems(id, wonder);
            storedItemsRepository.save(stored);
        }
        for(PotionInfo potion: selectedPotions) {
            StoredItems stored = new StoredItems(id, potion);
            storedItemsRepository.save(stored);
        }
        for(ScrollInfo scroll: selectedScrolls) {
            StoredItems stored = new StoredItems(id, scroll);
            storedItemsRepository.save(stored);
        }
    }

    public Shop(String id, WonderRepository wonderRepository, PotionRepository potionRepository,
                ScrollRepository scrollRepository, SessionRepository sessionRepository,
                StoredItemsRepository storedItemsRepository) {
        Gson gson = new Gson();
        this.wonders = wonderRepository;
        this.potions = potionRepository;
        this.scrolls = scrollRepository;
        this.sessionRepository = sessionRepository;
        this.storedItemsRepository = storedItemsRepository;
        this.id = id;
        this.discounts = new HashMap<>();
        this.basePrices = new HashMap<>();
        this.selectedWonders = new ArrayList<>();
        this.selectedPotions = new ArrayList<>();
        this.selectedScrolls = new ArrayList<>();
        Sessions shopSession = sessionRepository.getSessionBySessionId(id);
        List<StoredItems> itemList = storedItemsRepository.findAllBySessionId(id);
        HashMap discountsUnparsed = gson.fromJson(shopSession.getDiscounts(), HashMap.class);
        discountsUnparsed.forEach((k, v) -> discounts.put(Types.valueOf((String) k),((Double) v).intValue()));
        HashMap basePricesUnparsed = gson.fromJson(shopSession.getBasePrices(), HashMap.class);
        basePricesUnparsed.forEach((k, v) -> basePrices.put(Rarity.valueOf((String) k), ((Double) v).intValue()));
        for(StoredItems item : itemList) {
            if(item.getType().equals("Potion")) {
                Potion potion = potions.findById(item.getItemId()).get();
                PotionInfo potionInfo = new PotionInfo(potion, this);
                selectedPotions.add(potionInfo);
            } else if(item.getType().equals("Scroll")) {
                Scroll scroll = scrolls.findById(item.getItemId()).get();
                ScrollInfo scrollInfo = new ScrollInfo(scroll, this);
                selectedScrolls.add(scrollInfo);
            } else {
                Wonder wonder = wonders.findById(item.getItemId()).get();
                WonderInfo wonderInfo = new WonderInfo(wonder, this);
                selectedWonders.add(wonderInfo);
            }
        }
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
        if(sessionRepository.existsBySessionId(checkString)) {
            checkString = generateShopId();
        }
        return checkString;
    }

    private void generateWonders() {
        List<Wonder> list = wonders.findAll();
        for(int i = 0; i <  15; i++) {
            Wonder wonder = list.get(random.nextInt(list.size()));
            WonderInfo createdWonder = new WonderInfo(wonder, this);
            this.selectedWonders.add(createdWonder);
        }
    }

    private void generatePotions() {
        List<Potion> list = potions.findAll();
        for(int i = 0; i < 3; i++) {
            Potion potion = list.get(random.nextInt(list.size()));
            PotionInfo createdPotion = new PotionInfo(potion, this);
            this.selectedPotions.add(createdPotion);
        }
    }

    private void generateScrolls() {
        List<Scroll> list = scrolls.findAll();
        int numberOfScrolls = randomNum(3, 5);
        for(int i = 0; i < numberOfScrolls; i++) {
            Scroll scroll = list.get(random.nextInt(list.size()));
            ScrollInfo createdScroll = new ScrollInfo(scroll, this);
            this.selectedScrolls.add(createdScroll);
        }
    }

    private Integer randomNum(Integer min, Integer max) {
        return random.nextInt(max - min + 1) + min;
    }
}
