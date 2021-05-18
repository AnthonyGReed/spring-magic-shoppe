package com.adventureincpod.springmagicshoppe.webserver;

import com.adventureincpod.springmagicshoppe.webserver.DAOs.PotionDAO;
import com.adventureincpod.springmagicshoppe.webserver.DAOs.ScrollDAO;
import com.adventureincpod.springmagicshoppe.webserver.DAOs.WonderDAO;
import com.adventureincpod.springmagicshoppe.webserver.models.Item;
import com.adventureincpod.springmagicshoppe.webserver.models.Shop;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTController {
    @GetMapping("/api/newShop")
    public Shop newShop() { return new Shop(); }

    @GetMapping("/api/shopID")
    public Shop savedShop(@RequestParam String id) { return new Shop(id); }

    @GetMapping("/api/itemInfo")
    public Item getInfo(@RequestParam Integer id, @RequestParam String type) {
        if(type.equals("potion")) {
            PotionDAO potionDAO = new PotionDAO();
            return potionDAO.getInfo(id);
        } else if(type.equals("scroll")) {
            ScrollDAO scrollDAO = new ScrollDAO();
            return scrollDAO.getInfo(id);
        } else {
            WonderDAO wonderDAO = new WonderDAO();
            return wonderDAO.getInfo(id);
        }
    }
}
