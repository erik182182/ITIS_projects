package ru.erik182.models;

import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder

public class User {
    private Long id;
    private String fullName;
    private String passport;
    private String hashPassword;
    private String type;

    private Set<Exam> exams;
    private Set<Declaration> declarations;
}
