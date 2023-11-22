package com.khazova.velorentclients.model.entity;

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

    @Column(name = "title", length = 100, unique = true)
    private String title;

    @Column(name = "description", length = 1000)
    private String description;

    @OneToMany(mappedBy = "status")
    private List<Client> clients;

    @Override
    public String toString() {
        return "Status{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}