package com.adventureincpod.springmagicshoppe.webserver.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Wonder extends Item {
    Integer charges;
    Integer stones;
    String limits;
    String attunement;
}
