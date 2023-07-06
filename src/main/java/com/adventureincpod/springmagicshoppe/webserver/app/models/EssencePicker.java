package com.adventureincpod.springmagicshoppe.webserver.app.models;

import com.adventureincpod.springmagicshoppe.webserver.app.models.crud.Wonder;
import com.adventureincpod.springmagicshoppe.webserver.app.repos.WonderRepository;
import com.adventureincpod.springmagicshoppe.webserver.app.models.enums.Rarity;
import com.adventureincpod.springmagicshoppe.webserver.app.models.enums.Types;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

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

    public EssencePicker(Rarity rarity, Types type, WonderRepository wonders) {
        this.rarity = rarity;
        this.type = type;
        this.wonders = wonders;
        this.random = new Random();
        generateWonders();
    }

    private void generateWonders() {
        List<Wonder> list = wonders.findAllByRarity(rarity.getName());
        for(int i = 0; i < 3; i++) {
            List<Wonder> filteredList = list.stream()
                .filter(w -> w.getType().equals(type))
                .collect(Collectors.toList());
            Wonder wonder = filteredList.get(random.nextInt(filteredList.size()));
            this.selectedWonders.add(wonder);
        }
    }
}
