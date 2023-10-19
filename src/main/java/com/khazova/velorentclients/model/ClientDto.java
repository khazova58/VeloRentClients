package com.khazova.velorentclients.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    @NotBlank(message = "не должно быть пустым")
    private String surname;

    @NotBlank(message = "не должно быть пустым")
    private String name;

    @NotBlank(message = "не должно быть пустым")
    private String lastname;

    @Min(value = 18, message = "должен быть старше 18 лет")
    private int age;

    @Email
    private String email;

    @Pattern(regexp = "^(8|[+]7)[0-9]{10}$", message = "должно соответствовать записи (+7/8)9555555")
    private String phoneNumber;
}