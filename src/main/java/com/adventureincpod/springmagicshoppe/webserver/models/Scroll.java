package com.adventureincpod.springmagicshoppe.webserver.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Scroll extends Item{
    String level;
    Integer levelNum;
    String school;
    String spellRange;
    String duration;
    String components;
    Boolean concentration;
    Boolean ritual;
    String[] classes;
    String castingTime;
}
