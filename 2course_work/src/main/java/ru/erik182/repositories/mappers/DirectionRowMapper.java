package ru.erik182.repositories.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.erik182.models.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class DirectionRowMapper implements RowMapper<Direction> {
    @Override
    public Direction mapRow(ResultSet resultSet, int i) throws SQLException {
        Direction direction = Direction.builder()
                .id(resultSet.getLong("id"))
                .info(resultSet.getString("info"))
                .budgetPlaces(resultSet.getInt("budget_places"))
                .name(resultSet.getString("name"))
                .university(University.builder()
                        .id(resultSet.getLong("uni_id"))
                        .info(resultSet.getString("uni_info"))
                        .name(resultSet.getString("uni_name"))
                        .city(City.builder()
                                .id(resultSet.getLong("city_id"))
                                .name(resultSet.getString("city_id"))
                                .build())
                        .build())
                .build();
        long id = resultSet.getLong("id");
        Set<Exam> examsOfDirecion = new HashSet<>();
        do {
            examsOfDirecion.add(Exam.builder()
                .score(resultSet.getInt("min_score"))
                .subject(Subject.builder()
                        .id(resultSet.getLong("subj_id"))
                        .name(resultSet.getString("subj_name")).build())
                .build());
        }
        while ( resultSet.next() && resultSet.getLong("id") == id );
        direction.setExamsWithMinScore(examsOfDirecion);
        return direction;
    }
}
