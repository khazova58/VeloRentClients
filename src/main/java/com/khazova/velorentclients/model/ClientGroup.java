package com.khazova.velorentclients.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Entity
@Table(name = "client_groups", schema = "rental")
@Data
@IdClass(ClientGroup.ClientGroupPK.class)
public class ClientGroup {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @EqualsAndHashCode
    public static class ClientGroupPK implements Serializable {
        private Integer group;
        private String client;
    }

}
