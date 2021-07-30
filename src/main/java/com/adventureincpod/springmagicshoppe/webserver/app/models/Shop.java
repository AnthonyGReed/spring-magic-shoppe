package com.adventureincpod.springmagicshoppe.webserver.app.models;

import com.adventureincpod.springmagicshoppe.webserver.app.models.crud.*;
import com.adventureincpod.springmagicshoppe.webserver.app.repos.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.*;
import java.util.stream.Collectors;

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
    private ShopLevel shopLevel;
    private ArrayList<WonderInfo> selectedWonders;
    private ArrayList<PotionInfo> selectedPotions;
    private ArrayList<ScrollInfo> selectedScrolls;
    private HashMap<Rarity, Integer> basePrices;
    private HashMap<Types, Integer> discounts;
    @JsonIgnore
    private transient Random random;

    public Shop(WonderRepository wonderRepository, PotionRepository potionRepository, ScrollRepository scrollRepository,
                SessionRepository sessionRepository, StoredItemsRepository storedItemsRepository, Integer shopLevel) {
        this.wonders = wonderRepository;
        this.potions = potionRepository;
        this.scrolls = scrollRepository;
        this.sessionRepository = sessionRepository;
        this.storedItemsRepository = storedItemsRepository;
        this.shopLevel = selectShopLevel(shopLevel);
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
        Sessions session = new Sessions(id, discounts, basePrices, this.shopLevel);
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
                PotionInfo potionInfo = new PotionInfo(potion, item);
                selectedPotions.add(potionInfo);
            } else if(item.getType().equals("Scroll")) {
                Scroll scroll = scrolls.findById(item.getItemId()).get();
                ScrollInfo scrollInfo = new ScrollInfo(scroll, item);
                selectedScrolls.add(scrollInfo);
            } else {
                Wonder wonder = wonders.findById(item.getItemId()).get();
                WonderInfo wonderInfo = new WonderInfo(wonder, item);
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
            Rarity rarity = selectRarity(shopLevel);
            List<Wonder> filteredList = list.stream()
                    .filter(w -> w.getRarity().equals(rarity.getName()))
                    .collect(Collectors.toList());
            Wonder wonder = filteredList.get(random.nextInt(filteredList.size()));
            WonderInfo createdWonder = new WonderInfo(wonder, this);
            this.selectedWonders.add(createdWonder);
        }
    }

    private void generatePotions() {
        List<Potion> list = potions.findAll();
        for(int i = 0; i < 3; i++) {
            Rarity rarity = selectRarity(shopLevel);
            List<Potion> filteredList = list.stream()
                    .filter(p -> p.getRarity().equals(rarity.getName()))
                    .collect(Collectors.toList());
            Potion potion = filteredList.get(random.nextInt(filteredList.size()));
            PotionInfo createdPotion = new PotionInfo(potion, this);
            this.selectedPotions.add(createdPotion);
        }
    }

    private void generateScrolls() {
        List<Scroll> list = scrolls.findAll();
        int numberOfScrolls = randomNum(3, 5);
        for(int i = 0; i < numberOfScrolls; i++) {
            System.out.println("Creating Scrolls");
            Rarity rarity = selectRarity(shopLevel);
            Integer selectedSpellLevel = randomNum(rarity.getSpellLevelMin(), rarity.getSpellLevelMax());
            List<Scroll> filteredList = list.stream()
                    .filter(s -> (Integer.parseInt(s.getLevel().substring(0,1)))
                            <= selectedSpellLevel)
                    .collect(Collectors.toList());
            Scroll scroll = filteredList.get(random.nextInt(filteredList.size()));
            ScrollInfo createdScroll = new ScrollInfo(scroll, this, selectedSpellLevel, rarity);
            this.selectedScrolls.add(createdScroll);
        }
    }

    private Rarity selectRarity(ShopLevel shopLevel) {
        int roll = randomNum(1,15);
        if(roll == 1) {
            return shopLevel.getSLOT1();
        } else if(roll <= 3) {
            return shopLevel.getSLOT2();
        } else if(roll <= 6) {
            return shopLevel.getSLOT3();
        } else if(roll <= 10) {
            return shopLevel.getSLOT4();
        } else {
            return shopLevel.getSLOT5();
        }
    }

    private ShopLevel selectShopLevel(Integer shopLevel) {
        switch(shopLevel) {
            case 1:
                return ShopLevel.LEVEL1;
            case 2:
                return ShopLevel.LEVEL2;
            case 3:
                return ShopLevel.LEVEL3;
            case 4:
                return ShopLevel.LEVEL4;
            case 5:
                return ShopLevel.LEVEL5;
            case 6:
                return ShopLevel.LEVEL6;
            case 7:
                return ShopLevel.LEVEL7;
            case 8:
                return ShopLevel.LEVEL8;
            case 9:
                return ShopLevel.LEVEL9;
            default:
                return ShopLevel.LEVEL10;
        }
    }

    private Integer randomNum(Integer min, Integer max) {
        return random.nextInt(max - min + 1) + min;
    }

}
