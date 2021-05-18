package com.adventureincpod.springmagicshoppe.webserver;

import com.adventureincpod.springmagicshoppe.webserver.models.Shop;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTController {
    @GetMapping("/api/newShop")
    public Shop newShop() {
        return new Shop();
    }

}
