package ru.erik182.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.erik182.models.City;
import ru.erik182.repositories.mappers.CityRowMapper;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;

@Repository
public class CityRepositoryImpl implements CityRepository {

    private JdbcTemplate jdbcTemplate;

    private String SQL_GET_CITY = "SELECT * FROM city WHERE id = ?;";

    private String SQL_SAVE_CITY = "INSERT INTO city(name) VALUES(?);";

    private String SQL_DELETE_CITY = "DELETE FROM city WHERE id = ?;";

    private String SQL_UPDATE_CITY = "UPDATE city SET name = ? WHERE id = ?";

    private String SQL_GET_ALL_CITIES = "SELECT * FROM city;";

    @Autowired
    public CityRepositoryImpl(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<City> findOne(Long id) {
        return Optional.of(jdbcTemplate.queryForObject(SQL_GET_CITY, new CityRowMapper()));
    }

    @Override
    public void save(City model) {
        jdbcTemplate.update(SQL_SAVE_CITY, model.getName());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_CITY, id);
    }

    @Override
    public void update(Long id, City model) {
        jdbcTemplate.update(SQL_UPDATE_CITY, model.getName(), id);
    }

    @Override
    public List<City> findAll() {
        return jdbcTemplate.query(SQL_GET_ALL_CITIES, new CityRowMapper());
    }
}
