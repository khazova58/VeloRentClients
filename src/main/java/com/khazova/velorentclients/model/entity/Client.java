package com.khazova.velorentclients.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * Сущность Клиент
 */

@Entity
@Table(name = "clients", schema = "rental")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    /**
     * Фамилия
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * Имя
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * Отчество
     */
    @Column(name = "middle_name")
    private String middleName;

    /**
     * Возраст
     */
    @Column(name = "age")
    private int age;

    /**
     * Электронная почта
     */
    @Column(name = "email")
    private String email;

    /**
     * Номер телефона
     */
    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * Адрес клиента
     */
    @Column(name = "address")
    private String address;

    /**
     * Дата создания клиента
     */
    @Column(name = "create_date")
    @CreationTimestamp
    private LocalDateTime createDate;

    /**
     * Дата обновления клиента
     */
    @Column(name = "last_access")
    @UpdateTimestamp
    private LocalDateTime lastAccess;

    /**
     * Источник создания пользователя
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_id")
    private Source source;

    /**
     * Статус пользователя
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private Status status;

    /**
     * Уровень интереса
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "level_id")
    private LevelInterest levelInterest;

    /**
     * Кем приглашен пользователь
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "referrer_id")
    private Client referrer;

    @Override
    public String toString() {
        return "Client{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", createDate=" + createDate +
                ", lastAccess=" + lastAccess +
                '}';
    }
}