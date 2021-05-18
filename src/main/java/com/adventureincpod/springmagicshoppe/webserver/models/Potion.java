package com.adventureincpod.springmagicshoppe.webserver.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Potion extends Item {
    Integer doses;
}
