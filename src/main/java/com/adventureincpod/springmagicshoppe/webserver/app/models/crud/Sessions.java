package com.adventureincpod.springmagicshoppe.webserver.app.models.crud;

import com.adventureincpod.springmagicshoppe.webserver.app.models.Rarity;
import com.adventureincpod.springmagicshoppe.webserver.app.models.Types;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.HashMap;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Sessions {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private String sessionId;
    private String discounts;
    private String basePrices;

    public Sessions(String sessionId, HashMap<Types, Integer> discounts, HashMap<Rarity, Integer> basePrices) {
        Gson gson = new Gson();
        this.sessionId = sessionId;
        this.discounts = gson.toJson(discounts);
        this.basePrices = gson.toJson(basePrices);
    }
}
