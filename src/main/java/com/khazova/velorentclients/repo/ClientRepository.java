package com.khazova.velorentclients.repo;

import com.khazova.velorentclients.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {

    @Query(value = """
            SELECT c FROM Client c
            WHERE :surname IS NULL OR LOWER (c.surname) LIKE LOWER(CONCAT('%', :surname, '%'))
            """)
    List<Client> findAllByName(@Param("surname") String surname);
}
