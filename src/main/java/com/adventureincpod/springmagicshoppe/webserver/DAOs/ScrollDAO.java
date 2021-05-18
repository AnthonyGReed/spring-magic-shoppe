package com.adventureincpod.springmagicshoppe.webserver.DAOs;

import com.adventureincpod.springmagicshoppe.webserver.models.Item;
import com.adventureincpod.springmagicshoppe.webserver.models.Scroll;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class ScrollDAO implements ItemDAO{
    JdbcTemplate jdbcTemplate;

    public ScrollDAO(){
        jdbcTemplate = new JdbcTemplate();
    }

    @Override
    public List<Scroll> getAll() {
        return jdbcTemplate.query("SELECT id, name, level, level_num, page FROM spells",
                resultSet -> {
                    List<Scroll> list = new ArrayList<>();
                    while(resultSet.next()) {
                        Scroll scroll = new Scroll();
                        scroll.setId(resultSet.getInt(1));
                        scroll.setName(resultSet.getString(2));
                        scroll.setLevel(resultSet.getString(3));
                        scroll.setLevelNum(resultSet.getInt(4));
                        scroll.setPage(resultSet.getString(5));
                        list.add(scroll);
                    }
                    return list;
                });
    }

    @Override
    public Scroll getOne(Integer id) {
        return jdbcTemplate.query("SELECT name, level, level_num, page FROM spells WHERE id = " + id,
                resultSet -> {
                    Scroll scroll = new Scroll();
                    scroll.setId(id);
                    scroll.setName(resultSet.getString(1));
                    scroll.setLevel(resultSet.getString(2));
                    scroll.setLevelNum(resultSet.getInt(3));
                    scroll.setPage(resultSet.getString(4));
                    return scroll;
                });
    }

    @Override
    public Scroll getInfo(Integer id) {
        return jdbcTemplate.query(
                "SELECT name, school, spell_range, duration, components, concentration, ritual, classes, casting_time" +
                        " FROM spells WHERE id = " + id,
                resultSet -> {
                    Scroll scroll = new Scroll();
                    scroll.setId(id);
                    scroll.setName(resultSet.getString(1));
                    scroll.setSchool(resultSet.getString(2));
                    scroll.setSpellRange(resultSet.getString(3));
                    scroll.setDuration(resultSet.getString(4));
                    scroll.setConcentration(resultSet.getBoolean(5));
                    scroll.setRitual(resultSet.getBoolean(6));
                    scroll.setClasses(resultSet.getString(7).split(","));
                    scroll.setCastingTime(resultSet.getString(8));
                    return scroll;
                }
        );
    }
}