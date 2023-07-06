package com.adventureincpod.springmagicshoppe.webserver.app;

import com.adventureincpod.springmagicshoppe.webserver.app.models.Shop;
import com.adventureincpod.springmagicshoppe.webserver.app.models.EssencePicker;
import com.adventureincpod.springmagicshoppe.webserver.app.models.enums.Rarity;
import com.adventureincpod.springmagicshoppe.webserver.app.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTController {
    @Autowired
    WonderRepository wonderRepository;
    @Autowired
    PotionRepository potionRepository;
    @Autowired
    ScrollRepository scrollRepository;
    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    StoredItemsRepository storedItemsRepository;

    @GetMapping("/api/newShop")
    public String newShop(@RequestParam(value = "shopLevel") Integer shopLevel) {
        Shop shop = new Shop(wonderRepository,
            potionRepository, scrollRepository, sessionRepository, storedItemsRepository, shopLevel);
        return shop.getId();
    }

    @GetMapping("/api/shopID")
    public Shop savedShop(@RequestParam String id) { return new Shop(id, wonderRepository, potionRepository,
            scrollRepository, sessionRepository, storedItemsRepository); }

    @GetMapping("/api/essence")
    public EssencePicker newPicker(@RequestParam("rarity") Rarity rarity, @RequestParam String type ) {
        return new EssencePicker(rarity, type, wonderRepository);
    }

}
