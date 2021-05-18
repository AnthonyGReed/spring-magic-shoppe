package com.adventureincpod.springmagicshoppe.webserver.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Item {
    Integer id;
    String type;
    String name;
    String description;
    String page;
    Rarity rarity;
    Boolean onSale;
    Integer goldCost;
}
