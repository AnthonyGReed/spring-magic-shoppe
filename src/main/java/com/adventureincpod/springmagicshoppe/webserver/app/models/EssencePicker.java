package com.adventureincpod.springmagicshoppe.webserver.app.models;

import com.adventureincpod.springmagicshoppe.webserver.app.models.crud.Wonder;
import com.adventureincpod.springmagicshoppe.webserver.app.repos.WonderRepository;
import com.adventureincpod.springmagicshoppe.webserver.app.models.enums.Rarity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class EssencePicker {
    @JsonIgnore
    private transient WonderRepository wonders;
    private Rarity rarity;
    private String type;
    private ArrayList<Wonder> selectedWonders;
    @JsonIgnore
    private transient Random random;

    public EssencePicker(Rarity rarity, String type) {
        this.rarity = rarity;
        this.type = type;
        this.random = new Random();
        generateWonders();
    }

    private void generateWonders() {
        List<Wonder> list = wonders.findAll();
        for(int i = 0; i < 3; i++) {
            List<Wonder> filteredList = list.stream()
                .filter(w -> w.getRarity().equals(rarity.getName()))
                .filter(w -> w.getType().equals(type))
                .collect(Collectors.toList());
            Wonder wonder = filteredList.get(random.nextInt(filteredList.size()));
            this.selectedWonders.add(wonder);
        }
    }
}
