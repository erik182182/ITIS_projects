package ru.erik182.repositories.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.erik182.models.City;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CityRowMapper implements RowMapper<City> {
    @Override
    public City mapRow(ResultSet resultSet, int i) throws SQLException {
        return City.builder()
        .id(resultSet.getLong("id"))
        .name(resultSet.getString("name"))
        .build();
    }
}
