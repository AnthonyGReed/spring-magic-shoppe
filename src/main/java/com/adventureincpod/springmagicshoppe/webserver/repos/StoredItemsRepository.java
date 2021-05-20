package com.adventureincpod.springmagicshoppe.webserver.repos;

import com.adventureincpod.springmagicshoppe.webserver.models.crud.StoredItems;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StoredItemsRepository extends CrudRepository<StoredItems, Long> {
    List<StoredItems> findAll();
    List<StoredItems> findAllBySessionId(String sessionId);
}
