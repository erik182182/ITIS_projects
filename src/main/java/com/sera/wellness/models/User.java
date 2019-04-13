package com.sera.wellness.models;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Getter
@Setter
@Builder
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private boolean sex;
    private String email;
    private String hashPassword;
    private Boolean consentToReceiveEmails;
    private Integer age;
    private Integer weight, growth, purposeWeight;

}
