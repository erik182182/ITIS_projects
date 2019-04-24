package ru.erik182.models;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder

public class Subject {
    private Long id;
    private String name;
}
