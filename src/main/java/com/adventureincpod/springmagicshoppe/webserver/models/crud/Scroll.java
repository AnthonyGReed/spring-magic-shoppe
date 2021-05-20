package com.adventureincpod.springmagicshoppe.webserver.models.crud;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Scroll {
    private @Id @GeneratedValue Long id;
    private String name;
    private String type;
    private String school;
    private String spellRange;
    private String duration;
    private String components;
    private String level;
    private Boolean concentration;
    private Boolean ritual;
    private String classes;
    private String castingTime;
    private String page;

}
