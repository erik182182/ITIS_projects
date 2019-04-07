package ru.erik182.dto;


import lombok.Builder;
import lombok.Data;
import ru.erik182.models.Direction;
import ru.erik182.models.University;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Builder
@Data
public class UniversityDto {

    private long id;
    private String city;
    private String name;
    private String info;
    private Set<Direction> directions;

    public static UniversityDto from(University uni){
        return UniversityDto.builder()
                .id(uni.getId())
                .city(uni.getCity())
                .name(uni.getName())
                .directions(uni.getDirections())
                .info(uni.getInfo())
                .build();
    }

    public static List<UniversityDto> from(List<University> unis){
        return unis.stream().map(UniversityDto::from).collect(Collectors.toList());
    }
}
