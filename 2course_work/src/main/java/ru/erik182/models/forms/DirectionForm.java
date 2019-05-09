package ru.erik182.models.forms;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class DirectionForm {
    @NotNull
    private Long dirId;
}
