package ru.erik182.repositories.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.erik182.models.Exam;
import ru.erik182.models.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExamRowMapper implements RowMapper<Exam> {
    @Override
    public Exam mapRow(ResultSet resultSet, int i) throws SQLException {
        return Exam.builder()
                .id(resultSet.getLong("id"))
                .score(resultSet.getInt("score"))
                .subject(Subject.builder().name(resultSet.getString("name")).build())
                .build();
    }
}
