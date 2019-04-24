package ru.erik182.models.forms;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class SignInForm {
    private String passport;
    private String password;
    private boolean isRemember;
}
