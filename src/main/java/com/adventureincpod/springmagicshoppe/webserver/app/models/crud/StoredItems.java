package com.adventureincpod.springmagicshoppe.webserver.app.models.crud;

import com.adventureincpod.springmagicshoppe.webserver.app.models.PotionInfo;
import com.adventureincpod.springmagicshoppe.webserver.app.models.ScrollInfo;
import com.adventureincpod.springmagicshoppe.webserver.app.models.WonderInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class StoredItems {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private String sessionId;
    private Long itemId;
    private String type;
    private Integer gold;
    private Integer stones;
    private Integer charges;
    private Integer doses;
    private Boolean onSale;
    private Integer spellLevel;

    public StoredItems(String sessionId, WonderInfo wonder) {
        this.sessionId = sessionId;
        this.itemId = wonder.getWonder().getId();
        this.type = wonder.getWonder().getType();
        this.gold = wonder.getGold();
        this.stones = wonder.getStones();
        this.charges = wonder.getCharges();
        this.onSale = wonder.getOnSale();
    }

    public StoredItems(String sessionId, PotionInfo potion) {
        this.sessionId = sessionId;
        this.itemId = potion.getPotion().getId();
        this.type = potion.getPotion().getType();
        this.gold = potion.getGold();
        this.doses = potion.getDoses();
        this.onSale = potion.getOnSale();
    }

    public StoredItems(String sessionId, ScrollInfo scroll) {
        this.sessionId = sessionId;
        this.itemId = scroll.getScroll().getId();
        this.type = scroll.getScroll().getType();
        this.gold = scroll.getGold();
        this.onSale = scroll.getOnSale();
        this.spellLevel = scroll.getSpellLevel();
    }
}
