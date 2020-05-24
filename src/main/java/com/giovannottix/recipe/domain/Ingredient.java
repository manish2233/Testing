package com.giovannottix.recipe.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author: Giovanni Esposito.
 * @Date : 05/10/20, Sun
 */
@Data
@EqualsAndHashCode(exclude = {"recipe", "unitOfMeasure"})
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private BigDecimal amount;

    @ManyToOne
    private Recipe recipe;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure unitOfMeasure;

    public Ingredient(String description, BigDecimal amount,
                      UnitOfMeasure unitOfMeasure) {
        this.description = description;
        this.amount = amount;
        this.unitOfMeasure = unitOfMeasure;
    }

}
