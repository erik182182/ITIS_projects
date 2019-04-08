package ru.erik182.models;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder

public class University {
    private long id;
    private City city;
    private String name;
    private String info;
    private Set<Direction> directions;
}
