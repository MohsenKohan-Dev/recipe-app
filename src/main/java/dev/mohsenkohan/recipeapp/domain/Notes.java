package dev.mohsenkohan.recipeapp.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @MapsId
    private Recipe recipe;

    @Lob
    private String recipeNotes;
}
