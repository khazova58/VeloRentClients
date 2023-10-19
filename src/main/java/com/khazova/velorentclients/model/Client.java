package com.khazova.velorentclients.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "client")
@Data
@NoArgsConstructor
public class Client {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "surname")
    private String surname;

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

    public Client(String surname, String name, String lastname, int age, String email, String phoneNumber) {
        this.surname = surname;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}

