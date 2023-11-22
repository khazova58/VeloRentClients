package com.khazova.velorentclients.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Статус Клиента
 */
@Entity
@Table(name = "status", schema = "rental")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Status {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 100, unique = true)
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @OneToMany(mappedBy = "status")
    private List<Client> clients;

    @Override
    public String toString() {
        return "Status{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}