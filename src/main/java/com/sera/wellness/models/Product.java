package com.sera.wellness.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Product")
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    private String name,
            img;
    private Float protein,
            fats,
            carbohydrates,
            calories;
    private Boolean type;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
