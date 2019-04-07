package ru.erik182.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.erik182.models.Declaration;
import ru.erik182.models.Exam;
import ru.erik182.models.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String fullName;
    private String passport;
    private String hashPassword;
    private String type;

    private List<Exam> exams;
    private Set<Declaration> declarations;

    public static UserDto from(User user){
        return UserDto.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .passport(user.getPassport())
                .hashPassword(user.getHashPassword())
                .type(user.getType())
                .exams(user.getExams())
                .declarations(user.getDeclarations())
                .build();

    }

    public static List<UserDto> from(List<User> users){
        return users.stream().map(UserDto::from).collect(Collectors.toList());
    }
}
