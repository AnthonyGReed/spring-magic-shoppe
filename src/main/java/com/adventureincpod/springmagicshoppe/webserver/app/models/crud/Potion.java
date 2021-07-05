package com.adventureincpod.springmagicshoppe.webserver.app.models.crud;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Potion {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private String name;
    private String type;
    private String rarity;
    private String page;
    private String description;
}
