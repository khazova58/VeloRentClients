package com.khazova.velorentclients.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * С какого ресурса пришел Клиент
 */

@Entity
@Table(name = "source", schema = "rental")
@Getter
@Setter
@NoArgsConstructor
public class Source {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @OneToMany(mappedBy = "source")
    private List<Client> clients;

    @Override
    public String toString() {
        return "Source{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}