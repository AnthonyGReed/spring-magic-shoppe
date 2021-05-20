package com.adventureincpod.springmagicshoppe.webserver.repos;

import com.adventureincpod.springmagicshoppe.webserver.models.crud.Potion;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PotionRepository extends CrudRepository<Potion, Long> {
    List<Potion> findAll();
    Optional<Potion> findById(Long id);
}
