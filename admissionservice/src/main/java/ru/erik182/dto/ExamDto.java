package ru.erik182.dto;


import lombok.Builder;
import lombok.Data;
import ru.erik182.models.Exam;
import ru.erik182.models.University;
import ru.erik182.models.User;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class ExamDto {

    private Long id;
    private User user;
    private String subject;
    private int score;

    public static ExamDto from(Exam exam){
        return ExamDto.builder()
                .id(exam.getId())
                .user(exam.getUser())
                .subject(exam.getSubject())
                .score(exam.getScore())
                .build();
    }

    public static List<ExamDto> from(List<Exam> exams){
        return exams.stream().map(ExamDto::from).collect(Collectors.toList());
    }
}
