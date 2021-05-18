package com.adventureincpod.springmagicshoppe.webserver.DAOs;

import com.adventureincpod.springmagicshoppe.webserver.models.*;
import com.google.gson.Gson;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;

public class ShopDAO {
    JdbcTemplate jdbcTemplate;
    Gson gson;

    public ShopDAO() {
        jdbcTemplate = new JdbcTemplate();
        gson = new Gson();
    }

    public Boolean checkID(String id) {
        return jdbcTemplate.query("SELECT sessionId from sessions where sessionId = " + id,
            resultSet -> resultSet.getString(1) == null);
    }

    public void createSession(Shop shop){
        jdbcTemplate.update("INSERT INTO sessions (sessionId, adminId, discounts, basePrices) VALUES (?, 0, ?, ?)",
                shop.getId(), shop.getDiscounts(), shop.getBasePrices());
        for(Wonder wonder : shop.getWonders()) {
            jdbcTemplate.update("INSERT INTO storeditems (sessionId, itemId, type, gold, stones, charges, onSale) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)", shop.getId(), wonder.getId(), wonder.getType(),
                    wonder.getGoldCost(), wonder.getStones(), wonder.getCharges(), wonder.getOnSale());
        }
        for(Potion potion : shop.getPotions()) {
            jdbcTemplate.update("INSERT INTO storeditems (sessionId, itemId, type, gold, onSale, doses) " +
                    "VALUES (?, ?, ?, ?, ?, ?)", shop.getId(), potion.getId(), potion.getType(), potion.getGoldCost(),
                    potion.getOnSale(), potion.getDoses());
        }
        for(Scroll scroll : shop.getScrolls()) {
            jdbcTemplate.update("INSERT INTO storeditems (sessionId, itemId, type, gold, onSale, spellLevel) " +
                    "VALUES (?, ?, ?, ?, ?, ?)", shop.getId(), scroll.getId(), scroll.getType(), scroll.getGoldCost(),
                    scroll.getOnSale(), scroll.getLevelNum());
        }
    }

    public Shop getSession(String id) {
        Shop shop = new Shop();
        shop.setId(id);
        jdbcTemplate.query("SELECT discounts, basePrices FROM sessions WHERE sessionId = " + id,
                resultSet -> {
                    shop.setDiscounts(gson.fromJson(resultSet.getString(1), HashMap.class));
                    shop.setBasePrices(gson.fromJson(resultSet.getString(2), HashMap.class));
                });
        jdbcTemplate.query("SELECT itemId, type, gold, stones, charges, doses, onSale, " +
                        "spellLevel FROM storedItems WHERE sessionId = " + id,
                resultSet -> {
                    if(resultSet.getString(2).equals("Scroll")) {
                        Scroll scroll = new Scroll();
                        scroll.setId(resultSet.getInt(1));
                        scroll.setType(resultSet.getString(2));
                        scroll.setGoldCost(resultSet.getInt(3));
                        scroll.setOnSale(resultSet.getBoolean(7));
                        scroll.setLevelNum(resultSet.getInt(8));
                        shop.getItems().add(scroll);
                        shop.getScrolls().add(scroll);
                    } else if(resultSet.getString(2).equals("Potion")) {
                        Potion potion = new Potion();
                        potion.setId(resultSet.getInt(1));
                        potion.setType(resultSet.getString(2));
                        potion.setGoldCost(resultSet.getInt(3));
                        potion.setDoses(resultSet.getInt(4));
                        potion.setOnSale(resultSet.getBoolean(5));
                        shop.getItems().add(potion);
                        shop.getPotions().add(potion);
                    } else {
                        Wonder wonder = new Wonder();
                        wonder.setId(resultSet.getInt(1));
                        wonder.setType(resultSet.getString(2));
                        wonder.setGoldCost(resultSet.getInt(3));
                        wonder.setStones(resultSet.getInt(4));
                        wonder.setCharges(resultSet.getInt(5));
                        wonder.setOnSale(resultSet.getBoolean(6));
                        shop.getItems().add(wonder);
                        shop.getWonders().add(wonder);
                    }
                });
        return shop;
    }
}
