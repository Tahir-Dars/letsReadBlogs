package com.letsreadhere.blog.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;

    //cascade is not used becasue we are not g
    @ManyToMany(mappedBy = "tags")
    private Set<Posts> posts = new HashSet<>();


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(id, tag.id) && Objects.equals(name, tag.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
