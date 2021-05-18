package com.adventureincpod.springmagicshoppe.webserver.DAOs;

import com.adventureincpod.springmagicshoppe.webserver.models.Item;
import com.adventureincpod.springmagicshoppe.webserver.models.Potion;
import com.adventureincpod.springmagicshoppe.webserver.models.Rarity;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class PotionDAO implements ItemDAO{
    JdbcTemplate jdbcTemplate ;

    public PotionDAO() {
        jdbcTemplate = new JdbcTemplate();
    }

    @Override
    public List<Potion> getAll() {
        return jdbcTemplate.query("SELECT id, name, rarity, page FROM potions",
                resultSet -> {
                    List<Potion> list = new ArrayList<>();
                    while(resultSet.next()) {
                        Potion potion = new Potion();
                        potion.setId(resultSet.getInt(1));
                        potion.setName(resultSet.getString(2));
                        potion.setRarity(Rarity.valueOf(resultSet.getString(3)));
                        potion.setPage(resultSet.getString(4));
                        list.add(potion);
                    }
                    return list;
                }
        );
    }

    @Override
    public Potion getOne(Integer id) {
        return jdbcTemplate.query("SELECT name, rarity, page FROM potions WEHER id = " + id,
                resultSet -> {
                    Potion potion = new Potion();
                    potion.setId(id);
                    potion.setName(resultSet.getString(1));
                    potion.setRarity(Rarity.valueOf(resultSet.getString(2)));
                    potion.setPage(resultSet.getString(3));
                    return potion;
                });
    }

    @Override
    public Potion getInfo(Integer id) {
        return jdbcTemplate.query("SELECT name, description FROM potions WHERE id =" + id,
                resultSet -> {
                    Potion potion = new Potion();
                    potion.setId(id);
                    potion.setName(resultSet.getString(1));
                    potion.setDescription(resultSet.getString(2));
                    return potion;
                });
    }
}
