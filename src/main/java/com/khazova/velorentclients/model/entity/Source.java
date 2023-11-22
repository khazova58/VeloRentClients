package com.khazova.velorentclients.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * С какого ресурса пришел Клиент
 */

@Entity
@Table(name = "source", schema = "rental")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Source {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "description", length = 1000)
    private String description;

    @OneToMany(mappedBy = "source")
    private List<Client> clients;

    @Override
    public String toString() {
        return "Source{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}