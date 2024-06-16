package dev.showdown.db.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "table_entity")
public class TableEntity {

    @Id
    private String id;

    private String name;

    private String votingSystem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private UserEntity owner;

    @ManyToMany
    @JoinTable(
            name = "user_table",
            joinColumns = { @JoinColumn(name = "table_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    Set<UserEntity> participants;

}
