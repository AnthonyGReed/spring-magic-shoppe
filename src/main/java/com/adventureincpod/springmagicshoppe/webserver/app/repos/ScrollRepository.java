package com.adventureincpod.springmagicshoppe.webserver.app.repos;

import com.adventureincpod.springmagicshoppe.webserver.app.models.crud.Scroll;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ScrollRepository extends CrudRepository<Scroll, Long> {
    List<Scroll> findAll();
    Optional<Scroll> findById(Long id);
}
