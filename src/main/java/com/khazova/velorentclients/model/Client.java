package com.khazova.velorentclients.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "client")
@Data
public class Client {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "female")
    private String female;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "age")
    private int age;

    @Column(name = "email")
    private String email;

    @Column(name = "phonenumber")
    private String phoneNumber;
}

