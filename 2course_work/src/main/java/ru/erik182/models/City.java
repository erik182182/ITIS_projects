package ru.erik182.models;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class City {
    private Long id;
    private String name;
}
