package com.giovannottix.recipe.domain;

import lombok.*;

import javax.persistence.*;

/**
 * @author: Giovanni Esposito.
 * @Date : 05/10/20, Sun
 */
@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Recipe recipe;

    @Lob
    private String recipeNotes;

}
