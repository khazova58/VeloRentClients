package com.khazova.velorentclients.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "level_interest", schema = "rental")
@Getter
@Setter
@NoArgsConstructor
public class LevelInterest {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 100, unique = true)
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "weight")
    private Integer weight;

    @OneToMany(mappedBy = "levelInterest")
    private List<Client> clients;

    @Override
    public String toString() {
        return "LevelInterest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", weight=" + weight +
                '}';
    }
}