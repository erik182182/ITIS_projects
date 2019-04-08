package ru.erik182.models;

import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder

public class Direction {
    private Long id;
    private String name;
    private String info;
    private int budgetPlaces;
    private Set<Declaration> declarations;
    private University university;
    private Set<Exam> examsWithMinScore;
}
