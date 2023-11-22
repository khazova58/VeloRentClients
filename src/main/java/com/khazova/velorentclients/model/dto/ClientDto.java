package com.khazova.velorentclients.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    @NotBlank(message = "не должно быть пустым")
    private String lastName;//фамилия

    @NotBlank(message = "не должно быть пустым")
    private String firstName;//имя

    @NotBlank(message = "не должно быть пустым")
    private String middleName;//отчество

    @Min(value = 18, message = "должно быть старше 18 лет")
    private int age;

    @Email
    private String email;

    @Pattern(regexp = "^(8|[+]7)[0-9]{10}$", message = "должно соответствовать записи (+7/8)9555555")
    @NotBlank(message = "не должно быть пустым")
    private String phoneNumber;

    private String address;

    private Integer source;

    private String referrer;
}