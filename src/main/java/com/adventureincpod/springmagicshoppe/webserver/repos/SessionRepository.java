package com.adventureincpod.springmagicshoppe.webserver.repos;

import com.adventureincpod.springmagicshoppe.webserver.models.crud.Sessions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends CrudRepository<Sessions, Long> {
    Sessions getSessionBySessionId(String sessionId);
    Boolean existsBySessionId(String sessionId);
}
