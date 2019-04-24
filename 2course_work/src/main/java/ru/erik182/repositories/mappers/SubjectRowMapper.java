package ru.erik182.repositories.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.erik182.models.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectRowMapper implements RowMapper<Subject> {
    @Override
    public Subject mapRow(ResultSet resultSet, int i) throws SQLException {
        return Subject.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .build();
    }
}
