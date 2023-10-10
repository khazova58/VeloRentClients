package com.khazova.velorentclients.model;

public record ClientDto(String female,
                        String name,
                        String lastname,
                        int age,
                        String email,
                        String phoneNumber) {
}
