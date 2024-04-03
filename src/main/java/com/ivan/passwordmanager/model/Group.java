package com.ivan.passwordmanager.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "groups")
@Table(name = "groups")
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "groups")
    private List<Site> sitesList;

}
