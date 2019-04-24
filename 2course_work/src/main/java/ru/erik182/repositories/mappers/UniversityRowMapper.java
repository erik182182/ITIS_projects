package ru.erik182.repositories.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.erik182.models.City;
import ru.erik182.models.University;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UniversityRowMapper implements RowMapper<University> {
    @Override
    public University mapRow(ResultSet resultSet, int i) throws SQLException {
        return University.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .info(resultSet.getString("info"))
                .city(City.builder()
                        .id(resultSet.getLong("id_city"))
                        .name(resultSet.getString("city.name"))
                        .build())
                .build();
    }
}
