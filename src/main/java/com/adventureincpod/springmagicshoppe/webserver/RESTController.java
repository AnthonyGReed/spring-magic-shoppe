package com.adventureincpod.springmagicshoppe.webserver;

import com.adventureincpod.springmagicshoppe.webserver.models.Shop;
import com.adventureincpod.springmagicshoppe.webserver.repos.*;
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
    public Shop newShop() { return new Shop(wonderRepository, potionRepository, scrollRepository,
            sessionRepository, storedItemsRepository); }

    @GetMapping("/api/shopID")
    public Shop savedShop(@RequestParam String id) { return new Shop(id, wonderRepository, potionRepository, scrollRepository,
            sessionRepository, storedItemsRepository); }

}
