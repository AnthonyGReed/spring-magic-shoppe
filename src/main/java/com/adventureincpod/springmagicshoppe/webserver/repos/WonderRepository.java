package com.adventureincpod.springmagicshoppe.webserver.repos;

import com.adventureincpod.springmagicshoppe.webserver.models.crud.Wonder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WonderRepository extends CrudRepository<Wonder, Long> {
    List<Wonder> findAll();
    Optional<Wonder> findById(Long id);
}
