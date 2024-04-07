package com.ivan.passwordmanager.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Site extends BaseClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "site_id")
    private Long id;

    private String name;

    private String email;

    private String password;

    @OneToMany(mappedBy = "site")
    private List<Note> notes;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
}
