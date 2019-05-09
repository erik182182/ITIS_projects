package ru.erik182.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "direction")
public class Direction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String info;
    private int budgetPlaces;
    @OneToMany
    private Set<Declaration> declarations;
    @ManyToOne
    private University university;
    @OneToMany
    private Set<Exam> examsWithMinScore;
}
