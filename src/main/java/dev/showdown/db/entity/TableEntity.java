package dev.showdown.db.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "table_entity")
public class TableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String linkIdentifier;

    private String name;

    private String votingSystem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_entity_id", referencedColumnName = "id")
    private UserEntity owner;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_table", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "rdu_id")
    private Set<Long> usersIds;
}
