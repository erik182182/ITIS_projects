package ru.erik182.dto;

import lombok.Builder;
import lombok.Data;
import ru.erik182.models.Declaration;
import ru.erik182.models.Direction;
import ru.erik182.models.User;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class DeclarationDto {

    private Long id;
    private User user;
    private Direction direction;
    private int sumScore;

    public static DeclarationDto from(Declaration dec){
        return DeclarationDto.builder()
                .id(dec.getId())
                .user(dec.getUser())
                .direction(dec.getDirection())
                .sumScore(dec.getSumScore())
                .build();
    }

    public static List<DeclarationDto> from(List<Declaration> decs){
        return decs.stream().map(DeclarationDto::from).collect(Collectors.toList());
    }
}
