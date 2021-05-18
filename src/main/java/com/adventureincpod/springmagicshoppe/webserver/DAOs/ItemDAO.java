package com.adventureincpod.springmagicshoppe.webserver.DAOs;

import com.adventureincpod.springmagicshoppe.webserver.models.Item;

import java.util.List;

public interface ItemDAO {
    public <T extends Item> List<T> getAll();
    public <T extends Item> T getOne(Integer id);
    public <T extends Item> T getInfo(Integer id);
}
