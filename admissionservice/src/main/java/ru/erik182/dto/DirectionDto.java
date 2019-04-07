package ru.erik182.dto;


import lombok.Builder;
import lombok.Data;
import ru.erik182.models.Declaration;
import ru.erik182.models.Direction;
import ru.erik182.models.Exam;
import ru.erik182.models.University;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class DirectionDto {
    private Long id;
    private String name;
    private String info;
    private int budgetPlaces;
    private List<Declaration> declarations;
    private University university;
    private List<Exam> examsWithMinScore;

    public static DirectionDto from(Direction dir){
        return DirectionDto.builder()
                .id(dir.getId())
                .budgetPlaces(dir.getBudgetPlaces())
                .name(dir.getName())
                .info(dir.getInfo())
                .declarations(dir.getDeclarations())
                .university(dir.getUniversity())
                .examsWithMinScore(dir.getExamsWithMinScore())
                .build();
    }

    public static List<DirectionDto> from(List<Direction> dirs){
        return dirs.stream().map(DirectionDto::from).collect(Collectors.toList());
    }
}
