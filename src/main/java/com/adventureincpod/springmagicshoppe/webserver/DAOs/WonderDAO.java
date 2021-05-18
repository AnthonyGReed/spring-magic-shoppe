package com.adventureincpod.springmagicshoppe.webserver.DAOs;

import com.adventureincpod.springmagicshoppe.webserver.models.Rarity;
import com.adventureincpod.springmagicshoppe.webserver.models.Wonder;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.ArrayList;
import java.util.List;

public class WonderDAO implements ItemDAO{
    JdbcTemplate jdbcTemplate;

    public WonderDAO() {
        jdbcTemplate = new JdbcTemplate();
    }

    @Override
    public List<Wonder> getAll() {
        return jdbcTemplate.query("SELECT id, name, rarity, page FROM items",
                resultSet -> {
                    List<Wonder> list = new ArrayList<Wonder>();
                    while(resultSet.next()) {
                        Wonder wonder = new Wonder();
                        wonder.setId(resultSet.getInt(1));
                        wonder.setName(resultSet.getString(2));
                        wonder.setRarity(Rarity.valueOf(resultSet.getString(3)));
                        wonder.setPage(resultSet.getString(4));
                        list.add(wonder);
                    }
                    return list;
                }
        );
    }

    @Override
    public Wonder getOne(Integer id) {
        return jdbcTemplate.query("SELECT name, rarity, page, FROM items WHERE id = " + id,
                resultSet -> {
                    Wonder wonder = new Wonder();
                    wonder.setId(id);
                    wonder.setName(resultSet.getString(1));
                    wonder.setRarity(Rarity.valueOf(resultSet.getString(2)));
                    wonder.setPage(resultSet.getString(3));
                    return wonder;
                });
    }

    @Override
    public Wonder getInfo(Integer id) {
        return jdbcTemplate.query("SELECT name, description, limits, attunement FROM items WHERE id = " + id,
                resultSet -> {
                    Wonder wonder = new Wonder();
                    wonder.setId(id);
                    wonder.setName(resultSet.getString(1));
                    wonder.setDescription(resultSet.getString(2));
                    wonder.setLimits(resultSet.getString(3));
                    wonder.setAttunement(resultSet.getString(4));
                    return wonder;
                });
    }
}
