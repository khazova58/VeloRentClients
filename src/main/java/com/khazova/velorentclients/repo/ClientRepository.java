package com.khazova.velorentclients.repo;

import com.khazova.velorentclients.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {

    @Query(value = """
            SELECT c FROM Client c
            WHERE :lastName IS NULL OR LOWER (c.lastName) LIKE LOWER(CONCAT('%', :lastName, '%'))
            """)
    List<Client> findAllByName(@Param("lastName") String lastName);

    Optional<Client> findByEmail(String email);
}
